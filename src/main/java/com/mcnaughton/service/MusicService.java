package com.mcnaughton.service;

import com.mcnaughton.client.SpotifyClient;
import com.mcnaughton.client.TwitterClient;
import com.mcnaughton.client.spotifyModels.response.Playlist;
import com.mcnaughton.client.twitterModels.NewSongFlag;
import com.mcnaughton.exceptions.NoNewSongsException;
import org.joda.time.DateTime;
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

    public void addSongToPlaylist(String songUri) throws Exception {
        NewSongFlag acceptingNewSongs = twitterClient.acceptingNewSongs();
        if(acceptingNewSongs.isAcceptingNewSongs()){
            spotifyClient.addSongToPlaylist(songUri);
            //TODO add in some kind of notification to myself
        } else {
            throw new NoNewSongsException(acceptingNewSongs.getReason());
        }
    }
}
