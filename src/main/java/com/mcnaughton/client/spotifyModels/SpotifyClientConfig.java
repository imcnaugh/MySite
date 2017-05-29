package com.mcnaughton.client.spotifyModels;

import org.springframework.beans.factory.annotation.Value;

public class SpotifyClientConfig {

    @Value("${spotify.api.host}")
    private String authHost;

    @Value("${spotify.api.host}")
    private String apiHost;

    @Value("${spotify.apiBasePath}")
    private String apiBasePath;

    @Value("${spotify.requestAccessPath}")
    private String requestAccessPath;

    @Value("${spotify.redirectUr}")
    private String redirectUri;

    @Value("${spotify.clientId}")
    private String clientId;

    @Value("${spotify.clientSecret}")
    private String clientSecret;

    @Value("${spotify.stateString}")
    private String stateString;

    @Value("${spotify.myUserId}")
    private String userId;

    @Value("${spotify.playlistId}")
    private String playlistId;

    public String getAuthHost() {
        return authHost;
    }

    public String getApiHost() {
        return apiHost;
    }

    public String getRequestAccessPath() {
        return requestAccessPath;
    }

    public String getApiBasePath() {
        return apiBasePath;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getStateString() {
        return stateString;
    }

    public String getUserId() {
        return userId;
    }

    public String getPlaylistId() {
        return playlistId;
    }
}
