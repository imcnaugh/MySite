package com.mcnaughton.service;

import com.mcnaughton.client.SpotifyClient;
import com.mcnaughton.client.TwitterClient;
import com.mcnaughton.client.spotifyModels.response.*;
import com.mcnaughton.client.twitterModels.NewSongFlag;
import com.mcnaughton.exceptions.AddingDuplicateSongException;
import com.mcnaughton.exceptions.NoNewSongsException;
import com.mcnaughton.exceptions.SpotifyException;
import com.mcnaughton.exceptions.ValidationException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.times;

public class MusicServiceTest {
    @Mock
    private TwitterClient mockTwitterClient;

    @Mock
    private SpotifyClient mockSpotifyClient;

    @InjectMocks
    private MusicService service;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    private String validSongUri = "spotify:track:1rIFZk9tTUtHP3vULR5wXe";

    @Test
    public void happyPathTest() throws Exception {
        Mockito.when(mockTwitterClient.acceptingNewSongs()).thenReturn(new NewSongFlag(true, null));
        Mockito.when(mockSpotifyClient.getPlaylistTracks()).thenReturn(getEmptyPlaylist());
        Mockito.when(mockSpotifyClient.addSongToPlaylist(validSongUri)).thenReturn(201);

        service.addSongToPlaylist(validSongUri);

        Mockito.verify(mockTwitterClient, times(1)).acceptingNewSongs();
        Mockito.verify(mockSpotifyClient, times(1)).getPlaylistTracks();
        Mockito.verify(mockSpotifyClient, times(1)).addSongToPlaylist(validSongUri);
        Mockito.verify(mockTwitterClient, times(1)).pingMe("Somebody is adding a song!");
    }

    @Test(expected = ValidationException.class)
    public void invalidUriPatternTest() throws Exception {
        service.addSongToPlaylist("badUriFormat");
    }

    @Test(expected = NoNewSongsException.class)
    public void notAcceptingNewSongsTest() throws Exception {
        Mockito.when(mockTwitterClient.acceptingNewSongs()).thenReturn(new NewSongFlag(false, "no new songs"));

        service.addSongToPlaylist(validSongUri);

        Mockito.verify(mockTwitterClient, times(1)).acceptingNewSongs();
    }

    @Test(expected = AddingDuplicateSongException.class)
    public void addingDupSongTest() throws Exception {
        Mockito.when(mockTwitterClient.acceptingNewSongs()).thenReturn(new NewSongFlag(true, null));
        Mockito.when(mockSpotifyClient.getPlaylistTracks()).thenReturn(getPlaylistWithOneSong("1rIFZk9tTUtHP3vULR5wXe"));

        service.addSongToPlaylist(validSongUri);

        Mockito.verify(mockTwitterClient, times(1)).acceptingNewSongs();
        Mockito.verify(mockSpotifyClient, times(1)).getPlaylistTracks();
    }

    @Test(expected = SpotifyException.class)
    public void spotifyNotRespondingTest() throws Exception {
        Mockito.when(mockTwitterClient.acceptingNewSongs()).thenReturn(new NewSongFlag(true, null));
        Mockito.when(mockSpotifyClient.getPlaylistTracks()).thenReturn(getEmptyPlaylist());
        Mockito.when(mockSpotifyClient.addSongToPlaylist(validSongUri)).thenReturn(401);

        service.addSongToPlaylist(validSongUri);

        Mockito.verify(mockTwitterClient, times(1)).acceptingNewSongs();
        Mockito.verify(mockSpotifyClient, times(1)).getPlaylistTracks();
        Mockito.verify(mockSpotifyClient, times(1)).addSongToPlaylist(validSongUri);
    }

    //TODO add a test case for when twitter direct message fails

    private Playlist getEmptyPlaylist() {
        Playlist mockEmptyPlaylist = new Playlist();
        mockEmptyPlaylist.setTotal(0);
        mockEmptyPlaylist.setItems(new HashSet<Item>());
        return mockEmptyPlaylist;
    }

    private Playlist getPlaylistWithOneSong(String songId) {
        Album album = new Album();
        album.setName("Test Album");

        Set<Artist> artists = new HashSet<>();
        Artist artist = new Artist();
        artist.setName("Test Artist");
        artists.add(artist);

        Track track = new Track();
        track.setId(songId);
        track.setArtists(artists);
        track.setAlbum(album);
        track.setName("Test Name");

        Item existingSong = new Item();
        existingSong.setTrack(track);

        Set<Item> itemSet = new HashSet<>();
        itemSet.add(existingSong);

        Playlist mockPlaylist = new Playlist();
        mockPlaylist.setTotal(1);
        mockPlaylist.setItems(itemSet);

        return mockPlaylist;
    }
}
