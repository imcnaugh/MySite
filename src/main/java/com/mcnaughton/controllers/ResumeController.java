package com.mcnaughton.controllers;

import com.mcnaughton.models.resume.Resume;
import com.mcnaughton.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Resume getResume(){
        return resumeService.getMyResume();
    }
}
