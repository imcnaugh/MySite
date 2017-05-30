package com.mcnaughton.controllers;

import com.mcnaughton.models.links.Links;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/links")
public class LinksController {


    @ApiOperation("Links to things, some about me, some just things I think are cool.")
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Links getLinks(){
        return new Links();
    }
}
