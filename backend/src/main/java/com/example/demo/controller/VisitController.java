package com.example.demo.controller;

import com.example.demo.dao.VisitRepository;
import com.example.demo.dao.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/Visit")
public class VisitController {
    @Autowired(required = true)
    VisitService visitService;

}
