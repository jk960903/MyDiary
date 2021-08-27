package com.example.demo.controller;

import com.example.demo.JWT.JwtService;
import com.example.demo.SendMessage.SendMessage;
import com.example.demo.dao.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

///MyPage
@RestController
@RequestMapping(value="/api/myinfo")
public class MyInfoController {
    @Autowired(required = true)
    JwtService jwtService;

    @Autowired(required = true)
    MemberService memberService;



}
