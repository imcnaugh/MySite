package com.mcnaughton.client;

import com.mcnaughton.client.spotifyModels.response.AccessResponse;
import com.mcnaughton.client.spotifyModels.SpotifyClientConfig;
import com.mcnaughton.client.spotifyModels.response.Playlist;
import com.mcnaughton.exceptions.NotLoggedIntoSpotifyException;
import com.mcnaughton.util.FileUtil;
import com.mcnaughton.util.UriUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Repository
public class SpotifyClient {

    @Autowired
    private SpotifyClientConfig config;

    private static RestTemplate template = new RestTemplate();

    private static String accessToken;
    private static String tokenType;
    private static DateTime expireDate;

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
        String url = UriUtil.generateUri(config.getAuthHost(), config.getRequestAccessPath(),
                "grant_type=refresh_token",
                "refresh_token=" + getRefreshToken());
        AccessResponse response = template.postForObject(
                url,
                entity,
                AccessResponse.class);
        setTokens(response);
    }

    public Playlist getPlaylistTracks() throws NotLoggedIntoSpotifyException {
        if(expireDate == null || expireDate.isBeforeNow()){
            refreshAccessToken();
        }
        String url = UriUtil.generateUri(
                config.getApiHost(),
                config.getApiBasePath() + config.getUserId() + "/playlists/" + config.getPlaylistId() + "/tracks",
                "fields=total,items(track(id,name,album(name),artists(name)))");

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, "application/json");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        headers.add("Authorization", tokenType + " " + accessToken);
        HttpEntity<String> entity = new HttpEntity<>(null,headers);

        ResponseEntity<Playlist> response = template.exchange(url, HttpMethod.GET, entity, Playlist.class);

        return response.getBody();
    }

    public void addSongToPlaylist(String songUri) throws NotLoggedIntoSpotifyException {
        if(expireDate == null || expireDate.isBeforeNow()){
            refreshAccessToken();
        }

        String url = UriUtil.generateUri(
                config.getApiHost(),
                config.getApiBasePath() + config.getUserId() + "/playlists/" + config.getPlaylistId() + "/tracks",
                "uris=" + songUri);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", (tokenType + " " + accessToken));
        HttpEntity<String> entity = new HttpEntity<String>(null,headers);

        template.postForObject(url, entity, Object.class);
    }

    private void setTokens(AccessResponse response) {
        accessToken = response.getAccess_token();
        if(response.getRefresh_token() != null){
            setRefreshToken(response.getRefresh_token());}
        tokenType = response.getToken_type();
        expireDate = DateTime.now().plusSeconds(response.getExpires_in());
    }

    private String getEncodedClientInfo() {
        String s= config.getClientId() + ":" + config.getClientSecret();
        return Base64.getEncoder().encodeToString(s.getBytes());
    }

    public String getRefreshToken() {
        return FileUtil.getFileContents(config.getRefreshFilePath());
    }

    public void setRefreshToken(String refreshToken) {
        FileUtil.writeToFile(config.getRefreshFilePath(), refreshToken);
    }
}
