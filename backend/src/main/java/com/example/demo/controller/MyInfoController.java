package com.example.demo.controller;

import com.example.demo.JWT.JwtService;
import com.example.demo.SendMessage.SendMessage;
import com.example.demo.dao.MemberService;
import com.example.demo.vo.MyInfoVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

///MyPage
@RestController
@RequestMapping(value="/api/myinfo")
public class MyInfoController {

    private final JwtService jwtService;

    private final MemberService memberService;


    public MyInfoController(JwtService jwtService, MemberService memberService) {
        this.jwtService = jwtService;
        this.memberService = memberService;
    }

    @RequestMapping(value="/getmypageinfo")
    public ResponseEntity<SendMessage<MyInfoVO>> GetMyInfo(HttpServletRequest request){
        return null;
    }


}
