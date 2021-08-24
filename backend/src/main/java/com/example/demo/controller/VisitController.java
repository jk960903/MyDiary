package com.example.demo.controller;

import com.example.demo.JWT.JwtService;
import com.example.demo.dao.VisitService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/visit")
public class VisitController {
    private final VisitService visitService;


    private final JwtService jwtService;


    public VisitController(VisitService visitService, JwtService jwtService) {
        this.visitService = visitService;
        this.jwtService = jwtService;
    }
}
