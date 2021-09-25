package com.example.demo.controller;

import com.example.demo.JWT.JwtService;
import com.example.demo.dao.MemberService;
import com.example.demo.dao.VisitDetailService;
import com.example.demo.dao.VisitService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/visit")
public class VisitController {
    private final VisitService visitService;

    private final MemberService memberService;

    private final VisitDetailService visitDetailService;

    //게시글 댓글

    private final JwtService jwtService;


    public VisitController(VisitService visitService, MemberService memberService, VisitDetailService visitDetailService, JwtService jwtService) {
        this.visitService = visitService;
        this.memberService = memberService;
        this.visitDetailService = visitDetailService;
        this.jwtService = jwtService;
    }
}
