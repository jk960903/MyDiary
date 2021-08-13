package com.example.demo.controller;

import com.example.demo.JWT.JwtService;
import com.example.demo.vo.Enum.StatusEnum;
import com.example.demo.vo.SendMessage;
import io.jsonwebtoken.*;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;

import com.example.demo.vo.MemberVO;
import com.example.demo.dao.MemberService;
import com.example.demo.vo.LoginRequestVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/Login")
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired(required = true)
    MemberService memberService;

    @Autowired(required = true)
    JwtService jwtService;

    @RequestMapping(value="/")
    public String Test() {
        return "Test Page";
    }

    @RequestMapping(value ="/FindByID" ,method = RequestMethod.GET)
    public MemberVO FindByID(@RequestParam String ID) {
        MemberVO result;
        try {
            result = memberService.findByID(ID).get(0);
            return result;
        }catch(Exception e) {

        }
        return null;
    }

    @RequestMapping(value ="/FindByEmail", method = RequestMethod.GET)
    public MemberVO FindByEmail(@RequestParam String email) {
        MemberVO result;
        try {
            result = memberService.findByEmail(email).get(0);

            //null이 아니라면 메일 보내기
        }catch(Exception e) {
            result = null;
        }
        return result;
    }

    @RequestMapping(value ="/MakeAccount", method = RequestMethod.POST)
    public Integer MakeAccount(MemberVO memberVO) {
        memberVO.setIsdeleted(Byte.parseByte("1"));
        return memberService.MakeAccount(memberVO);
    }

    @RequestMapping(value = "/Login", method = RequestMethod.GET)
    public ModelAndView Login(@CookieValue(value="jwttoken",defaultValue ="",required = true) String jwt,
                              HttpServletRequest request) {
        Map<String,Object> login = jwtService.getUserID(jwt);
        String id = null , pwd =null;
        MemberVO User = null;
        Cookie cookie;
        if(login != null){
            id = login.get("ID").toString();
            pwd = login.get("pwd").toString();
            try{
                User = memberService.Login(id,pwd).get(0);
            }
            catch(Exception e){
                return new ModelAndView("login");
            }
            return new ModelAndView("main");
        }
        // 토큰 만료 혹은 로그아웃되어있는 상태
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/LoginAction", method = RequestMethod.GET)
    public ResponseEntity<SendMessage<String>> LoginAction(@CookieValue(value="jwttoken",defaultValue = "",required = true) String jwt,
                                                                       LoginRequestVO loginRequestVO,
                                                                        HttpServletResponse response){

        MemberVO memberVO;
        SendMessage<String> message = null;
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        String token = null;
        Cookie cookie= null;
        if(!jwt.equals("")){//토큰이있음
            Map<String,Object> map = jwtService.getUserID(jwt);
            if(map!=null){
                message = new SendMessage<>("",StatusEnum.UNAUTHORIZED,"토큰 만료로 인해 로그아웃");
                cookie = new Cookie("jwttoken","");
                cookie.setPath("/");
                response.addCookie(cookie);
                return new ResponseEntity<>(message,headers,HttpStatus.UNAUTHORIZED);
            }


        }else{
            try{
                memberVO= memberService.Login(loginRequestVO.getUserID(),loginRequestVO.getPassword()).get(0);
                if(memberVO !=null){
                    if(loginRequestVO.getAutologin()==null){
                        token =jwtService.createLoginToken(memberVO,1);
                        cookie = new Cookie("jwttoken",token);
                        cookie.setMaxAge(60*60*24*30);
                    }else {
                        token = jwtService.createLoginToken(memberVO, 0);
                        cookie = new Cookie("jwttoken",token);
                        cookie.setMaxAge(60*60*24);
                    }

                }
            }catch(Exception e){
                message = new SendMessage<>(null, StatusEnum.INTERNAL_SERVER_ERROOR,"로그인 에러");
                return new ResponseEntity<>(message,headers, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            cookie.setPath("/");
            response.addCookie(cookie);

        }
        return new ResponseEntity<>(message,headers,HttpStatus.OK);
    }

    @RequestMapping(value="/TestJwt")
    public String TestJwt(LoginRequestVO model,
                          @CookieValue(value="tempjwt",defaultValue = "", required =true) String jwt,
                          HttpServletResponse response){
        MemberVO result =  memberService.Login("1234","1234").get(0);
        if(result !=null){
            if(jwt.equals("")){//jwt 있음
                jwt = jwtService.createLoginToken(result,1);
                Cookie cookie = new Cookie("tempjwt",jwt);
                cookie.setPath("/");
                cookie.setMaxAge(60*60*24*30);
                response.addCookie(cookie);
            }else{
                Map<String,Object> results = jwtService.getUserID(jwt);
                LinkedHashMap<String,Object> maps = (LinkedHashMap<String,Object>)results.get("member");

                System.out.println("check");
            }
        }

        return jwt;
    }
}
