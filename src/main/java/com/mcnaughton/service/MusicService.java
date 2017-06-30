package com.mcnaughton.service;

import com.mcnaughton.client.SpotifyClient;
import com.mcnaughton.client.TwitterClient;
import com.mcnaughton.client.spotifyModels.response.Item;
import com.mcnaughton.client.spotifyModels.response.Playlist;
import com.mcnaughton.client.spotifyModels.response.Track;
import com.mcnaughton.client.twitterModels.NewSongFlag;
import com.mcnaughton.exceptions.AddingDuplicateSongException;
import com.mcnaughton.exceptions.NoNewSongsException;
import com.mcnaughton.exceptions.SpotifyException;
import com.mcnaughton.exceptions.ValidationException;
import jdk.nashorn.internal.runtime.regexp.joni.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import twitter4j.TwitterException;

import java.util.Optional;
import java.util.regex.Pattern;

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

    public Playlist getPlaylist(){
        return spotifyClient.getPlaylistTracks();
    }

    public void addSongToPlaylist(String songUri) throws Exception {
        Pattern songUriPattern = Pattern.compile("spotify:track:.{22}");

        if(!songUriPattern.matcher(songUri).matches()){
            throw new ValidationException();
        }

        NewSongFlag acceptingNewSongs = twitterClient.acceptingNewSongs();
        if(!acceptingNewSongs.isAcceptingNewSongs()){
            throw new NoNewSongsException(acceptingNewSongs.getReason());
        }

        Optional<Track> track = getPlaylist().getItems().stream().map(Item::getTrack).filter(t -> t.getId().equals(songUri.substring(14))).findFirst();
        if(track.isPresent()){
            throw new AddingDuplicateSongException(track.get());
        }

        int responseCode = spotifyClient.addSongToPlaylist(songUri);

        if(responseCode != 201){
            throw new SpotifyException(responseCode);
        }

        try{
            twitterClient.pingMe("Somebody is adding a song!");
        } catch (TwitterException te){
            //TODO log error
        }
    }
}
