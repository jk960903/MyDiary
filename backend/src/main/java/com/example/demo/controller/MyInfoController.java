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
@RequestMapping(value="/MyInfo")
public class MyInfoController {
    @Autowired(required = true)
    JwtService jwtService;

    @Autowired(required = true)
    MemberService memberService;

    @RequestMapping(value= "/MyPage")
    public ModelAndView MyPage(HttpServletRequest request){
        Map<String, Object> auth = jwtService.requestAuthorization(request);
        SendMessage<List<MemberVO>> sendMessage = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        if(auth == null){
            return new ModelAndView("login");
        }
        return new ModelAndView("MyPage");

    }
    @RequestMapping
}
