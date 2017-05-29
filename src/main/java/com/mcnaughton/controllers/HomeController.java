package com.mcnaughton.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/")
public class HomeController {

    @RequestMapping()
    public void redirectToSwagger(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html#!/");
    }
}
