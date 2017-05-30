package com.mcnaughton.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class AboutController {

    private final String ABOUT_ME_STRING =
            "This is my little slice of the web! Hi! I'm a programmer who loves to travel and see new things. I have been coding \n" +
            "all across the country, learning and refining my skills as I go. This site is more of a testing ground/play \n" +
            "area for me, but I suppose it also shows off what I can do in a spare weekend.\n\n" +
            "Poke around enough and you will find what I have been working on, want a copy of my resume in json format? \n" +
            "Its in the resume controller, want to pass me some sweet tunes via spotify, that's in the music controller. \n" +
            "Want to make a feature request, or get in touch with me? Well I haven't built that just yet, so your best off \n" +
            "just getting my email off the resume and getting in touch with me that way.\n\n";

    @ApiOperation(value = "What is this? and why do we need it?")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getAbout(){
        return ABOUT_ME_STRING;
    }
}
