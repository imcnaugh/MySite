package com.mcnaughton.controllers;

import com.mcnaughton.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/spotify/auth/")
public class SpotifyAuthController {

    @Autowired
    private MusicService musicService;

    @RequestMapping(method = RequestMethod.GET)
    public void getAuthCode(
            @RequestParam(required = false) String code,
            @RequestParam String state,
            @RequestParam(required = false) String error){
        musicService.setAuthCode(code, state, error);
    }
}
