package com.mcnaughton.controllers;

import com.mcnaughton.models.resume.Resume;
import com.mcnaughton.service.ResumeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @ApiOperation(value = "Its my resume, only its in JSON, why bother with stressful resume formatting when you could " +
            "could just get the raw data, that's what people are really after, right?")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Resume getResume(){
        return resumeService.getMyResume();
    }
}
