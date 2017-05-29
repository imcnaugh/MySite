package com.mcnaughton.controllers;

import com.mcnaughton.client.spotifyModels.response.Playlist;
import com.mcnaughton.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.TwitterException;

@RestController
@RequestMapping(value = "/api/music/")
public class MusicController {

    @Autowired
    private MusicService musicService;

    @RequestMapping(method = RequestMethod.GET)
    public Playlist testing() throws Exception {
        return musicService.getPlaylist();
    }
}
