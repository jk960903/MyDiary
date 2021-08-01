package com.example.demo.controller;

import com.example.demo.JWT.JwtService;
import com.example.demo.dao.VisitDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/VisitDetail")
public class VisitDetailController {
    @Autowired(required = true)
    VisitDetailService visitDetailService;

    @Autowired(required = true)
    JwtService jwtService;

}
