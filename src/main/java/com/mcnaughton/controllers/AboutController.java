package com.mcnaughton.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class AboutController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getAbout(){
        return "This is my about test";
    }
}
