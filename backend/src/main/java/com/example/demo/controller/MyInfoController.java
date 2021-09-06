package com.example.demo.controller;

import com.example.demo.JWT.JwtService;
import com.example.demo.SendMessage.SendMessage;
import com.example.demo.dao.MemberService;
import com.example.demo.vo.Enum.StatusEnum;
import com.example.demo.vo.MyInfo.MyInfoVO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.Map;

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
    
    //아직 미완성
    @RequestMapping(value="/getmypageinfo")
    public ResponseEntity<SendMessage<MyInfoVO>> GetMyInfo(HttpServletRequest request){
        SendMessage<MyInfoVO> message = null;
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> auth;

        try{
            auth = jwtService.requestAuthorization(request);
        }catch(IllegalAccessException e){
            message= new SendMessage<>(null, StatusEnum.UNAUTHORIZED,e.getMessage());
            return new ResponseEntity<>(message,headers, HttpStatus.UNAUTHORIZED);
        }
        return null;
    }


}
