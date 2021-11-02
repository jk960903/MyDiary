package com.example.demo.controller;

import com.example.demo.JWT.JwtService;
import com.example.demo.dao.VisitDetailService;
import jdk.jfr.Name;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/visit-detail")
@NoArgsConstructor
public class VisitDetailController {

    private  VisitDetailService visitDetailService;

    private  JwtService jwtService;

    @Autowired
    public VisitDetailController(VisitDetailService visitDetailService, JwtService jwtService) {
        this.visitDetailService = visitDetailService;
        this.jwtService = jwtService;
    }
}
