package com.example.demo.controller;

import com.example.demo.JWT.JwtService;
import com.example.demo.vo.SendMessage;
import io.jsonwebtoken.*;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.example.demo.vo.MemberVO;
import com.example.demo.dao.MemberService;
import com.example.demo.vo.LoginRequestVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ModelAndView Login() {
        return new ModelAndView("login");
    }

    /*@RequestMapping(value = "/LoginAction", method = RequestMethod.GET)
    public ResponseEntity<SendMessage<String>>*/

    @RequestMapping(value="/TestJwt")
    public String TestJwt(LoginRequestVO model,
                          @CookieValue(value="tempjwt",defaultValue = "", required =true) String jwt,
                          HttpServletResponse response){
        MemberVO result =  memberService.Login("1234","1234").get(0);
        if(result !=null){
            if(jwt.equals("")){//jwt 있음
                jwt = jwtService.createLoginToken(result);
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
