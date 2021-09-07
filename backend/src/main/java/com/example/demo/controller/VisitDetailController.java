package com.example.demo.controller;

import com.example.demo.JWT.JwtService;
import com.example.demo.dao.VisitDetailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="api/visitdetail")
public class VisitDetailController {

    private final VisitDetailService visitDetailService;

    private final JwtService jwtService;

    public VisitDetailController(VisitDetailService visitDetailService, JwtService jwtService) {
        this.visitDetailService = visitDetailService;
        this.jwtService = jwtService;
    }
}
