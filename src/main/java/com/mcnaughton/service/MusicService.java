package com.mcnaughton.service;

import com.mcnaughton.client.SpotifyClient;
import com.mcnaughton.client.TwitterClient;
import com.mcnaughton.client.spotifyModels.response.Playlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import twitter4j.TwitterException;

@Service
public class MusicService {

    @Autowired
    private TwitterClient twitterClient;

    @Autowired
    private SpotifyClient spotifyClient;

    @Value("${spotify.stateString}")
    private String authStateString;

    public void testing() throws TwitterException {
        boolean isAcceptingNewSongs = twitterClient.acceptingNewSongs();
    }

    public void setAuthCode(
            String authToken,
            String state,
            String error){
        if(error == null && state.equals(authStateString)){
            spotifyClient.requestAccessToken(authToken);
        }
    }

    public Playlist getPlaylist() throws Exception {
        return spotifyClient.getPlaylistTracks();
    }
}
