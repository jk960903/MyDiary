package com.example.demo.controller;

import com.example.demo.JWT.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Jwts;
import java.io.UnsupportedEncodingException;

import com.example.demo.vo.MemberVO;
import com.example.demo.dao.MemberService;
import com.example.demo.vo.LoginRequestVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
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
    public ModelAndView Login(LoginRequestVO model,
                              @CookieValue(value="id", defaultValue="", required=true) String id,
                              @CookieValue(value="pwd", defaultValue="", required=true) String pwd,
                              @CookieValue(value="autologin", defaultValue="0", required=true) String auto,
                              final HttpSession session,
                              HttpServletResponse response) {
        MemberVO result;
        if(id.equals("")&&pwd.equals("")){
            result =  memberService.Login(model.getUserID(),model.getPassword()).get(0);
        }
        else{
            result=memberService.Login(id,pwd).get(0);
        }

        //오토로그인 체크하고 오토로그인이 되어있으며 로그인 성공

        String jwt = jwtService.createLoginToken(result);

        if(Integer.parseInt(auto)!=0 && model.getAutologin().equals("1") && result!=null) {

            return new ModelAndView("Main");

        }
        //오토로그인 체크했으나 쿠키는 없고 로그인은 성공 했으니 쿠키생성
        else if(Integer.parseInt(auto)==0 &&model.getAutologin().equals("1") && result!=null) {
            Cookie[] cookies = new Cookie[3];
            cookies[0] = new Cookie("id", model.getUserID());
            cookies[1] = new Cookie("pwd", model.getPassword());
            cookies[2] = new Cookie("autologin", model.getAutologin());
            for(int i = 0 ; i<3; i++) {
                cookies[i].setPath("/");
                cookies[i].setMaxAge(60*60*24*30);
                response.addCookie(cookies[i]);
            }
        }

        else {
            return new ModelAndView("Login");
        }
        if(result!=null) {
            session.setAttribute("member", result);
            session.setMaxInactiveInterval(60*30);
        }

        return new ModelAndView("Main");
    }

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
                MemberVO member = jwtService.getUserID(jwt);
                System.out.println(member.getID());
            }
        }

        return jwt;
    }
}
