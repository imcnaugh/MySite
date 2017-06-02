package com.mcnaughton.controllers;

import com.mcnaughton.client.spotifyModels.response.Playlist;
import com.mcnaughton.service.MusicService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/music/")
public class MusicController {

    @Autowired
    private MusicService musicService;

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String getAbout(){
        return "This is a quick example of interacting with other services. Behind the endpoint, the service interacts with \n" +
                "both twitter and Spotify. Spotify to add songs to a playlist, and return that playlist. and Twitter to act as a \n" +
                "feature flag, Let's say somebody starts spamming bad music. I can tweet to an account, this service will pick that up \n" +
                "and either shut down or enable the feature.";
    }

    @ApiOperation(value = "Returns the songs currently in the playlist")
    @RequestMapping(method = RequestMethod.GET)
    public Playlist getPlaylist() throws Exception {
        return musicService.getPlaylist();
    }

    @ApiOperation(value = "Adds a new song to the playlist")
    @RequestMapping(method = RequestMethod.POST)
    public void addSongToPlaylist(
            @ApiParam(value = "the spotify uri of the song", required = true, defaultValue = "spotify:track:6SmPPtaMnfxgz5duA6t9Cu")
            @RequestParam String spotifyUri) throws Exception {
        musicService.addSongToPlaylist(spotifyUri);
    }
}
