package com.mcnaughton.controllers;

import com.mcnaughton.client.spotifyModels.response.Playlist;
import com.mcnaughton.service.MusicService;
import com.sun.org.apache.regexp.internal.RE;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.TwitterException;

@RestController
@RequestMapping(value = "/api/music/")
public class MusicController {

    @Autowired
    private MusicService musicService;

    @RequestMapping(method = RequestMethod.GET)
    public Playlist getPlaylist() throws Exception {
        return musicService.getPlaylist();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addSongToPlaylist(@RequestParam String spotifyUri) throws Exception {
        musicService.addSongToPlaylist(spotifyUri);
    }
}
