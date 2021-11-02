package com.example.demo.controller;

import com.example.demo.JWT.JwtService;
import com.example.demo.dao.MemberService;
import com.example.demo.dao.VisitDetailService;
import com.example.demo.dao.VisitService;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/visit")
@NoArgsConstructor
public class VisitController {
    private  VisitService visitService;

    private  MemberService memberService;

    private  VisitDetailService visitDetailService;

    //게시글 댓글

    private  JwtService jwtService;


    public VisitController(VisitService visitService, MemberService memberService, VisitDetailService visitDetailService, JwtService jwtService) {
        this.visitService = visitService;
        this.memberService = memberService;
        this.visitDetailService = visitDetailService;
        this.jwtService = jwtService;
    }
}
