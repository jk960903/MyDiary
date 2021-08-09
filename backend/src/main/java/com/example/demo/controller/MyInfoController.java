package com.example.demo.controller;

import com.example.demo.JWT.JwtService;
import com.example.demo.dao.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

///MyPage
@RestController
@RequestMapping(value="/MyInfo")
public class MyInfoController {
    @Autowired(required = true)
    JwtService jwtService;

    @Autowired(required = true)
    MemberService memberService;

    @RequestMapping(value= "/MyPage")
    public ModelAndView MyPage(){
        
    }
}
