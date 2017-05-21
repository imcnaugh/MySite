package com.mcnaughton.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class AboutController {

    private final String ABOUT_ME_STRING = "I'm a programmer who loves to travel and see new things. I have been coding" +
            "all across the country, learning and refining my skills as I go. ";

    @ApiOperation(value = "About me")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getAbout(){
        return ABOUT_ME_STRING;
    }
}
