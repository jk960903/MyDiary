package com.example.demo.controller;

import com.example.demo.JWT.JwtService;
import com.example.demo.dao.MemberService;
import com.example.demo.vo.MemberVO;
import com.example.demo.vo.SendMessage;
import com.example.demo.vo.notice.NoticeReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

///MyPage
@RestController
@RequestMapping(value="/api/myinfo")
public class MyInfoController {
    @Autowired(required = true)
    JwtService jwtService;

    @Autowired(required = true)
    MemberService memberService;


}
