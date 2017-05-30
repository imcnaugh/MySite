package com.mcnaughton.client;

import com.mcnaughton.client.spotifyModels.AccessResponse;
import com.mcnaughton.client.spotifyModels.SpotifyClientConfig;
import com.mcnaughton.client.spotifyModels.response.Playlist;
import com.mcnaughton.util.UriUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import sun.net.util.URLUtil;

import java.util.Base64;

@Repository
public class SpotifyClient {

    @Autowired
    private SpotifyClientConfig config;

    private RestTemplate template = new RestTemplate();

    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private DateTime expireDate;

    public void requestAccessToken(String authToken){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + getEncodedClientInfo());
        HttpEntity<String> entity = new HttpEntity<String>(null,headers);

        String url = UriUtil.generateUri(config.getAuthHost(), config.getRequestAccessPath(),
                "grant_type=authorization_code",
                "code=" + authToken,
                "redirect_uri=" + config.getRedirectUri());

        AccessResponse response = template.postForObject(
                url,
                entity,
                AccessResponse.class);

        setTokens(response);
    }

    private void refreshAccessToken(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + getEncodedClientInfo());
        HttpEntity<String> entity = new HttpEntity<String>(null,headers);

        AccessResponse response = template.postForObject(
                UriUtil.generateUri(config.getAuthHost(), config.getRequestAccessPath(),
                        "grant_type=refresh_token",
                        "code=" + refreshToken),
                entity,
                AccessResponse.class);
        setTokens(response);
    }

    private void setTokens(AccessResponse response) {
        accessToken = response.getAccess_token();
        refreshToken = response.getRefresh_token();
        tokenType = response.getToken_type();
        expireDate = DateTime.now().plusSeconds(response.getExpires_in());
    }

    public Playlist getPlaylistTracks() throws Exception {
        if(expireDate == null){
            //TODO get better errors
            throw new Exception();
        }

        if(expireDate.isBeforeNow()){
            refreshAccessToken();
        }
        String url = UriUtil.generateUri(
                config.getApiHost(),
                config.getApiBasePath() + config.getUserId() + "/playlists/" + config.getPlaylistId() + "/tracks",
                "fields=total,items(track(name,href,album(name,href)))");

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, "application/json");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        headers.add("Authorization", tokenType + " " + accessToken);
        HttpEntity<String> entity = new HttpEntity<>(null,headers);

        ResponseEntity<Playlist> response = template.exchange(url, HttpMethod.GET, entity, Playlist.class);

        return response.getBody();
    }

    public void addSongToPlaylist(String songUri) throws Exception {
        if(expireDate == null){
            //TODO get better errors
            throw new Exception();
        }

        if(expireDate.isBeforeNow()){
            refreshAccessToken();
        }

        String url = UriUtil.generateUri(
                config.getApiHost(),
                config.getApiBasePath() + config.getUserId() + "/playlists/" + config.getPlaylistId() + "/tracks",
                "uris=" + songUri);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        headers.add("Authorization", tokenType + " " + accessToken);
        HttpEntity<String> entity = new HttpEntity<>(null,headers);

        template.postForObject(url, entity, Object.class);
    }

    private String getEncodedClientInfo() {
        String s= config.getClientId() + ":" + config.getClientSecret();
        return Base64.getEncoder().encodeToString(s.getBytes());
    }
}
