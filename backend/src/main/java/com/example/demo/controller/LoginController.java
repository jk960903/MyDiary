package com.example.demo.controller;

import com.example.demo.JWT.JwtService;
import com.example.demo.vo.Enum.StatusEnum;
import com.example.demo.SendMessage.SendMessage;

import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;

import com.example.demo.vo.MemberVO;
import com.example.demo.dao.MemberService;
import com.example.demo.vo.LoginRequestVO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(value="/api/login")
public class LoginController {

    private final MemberService memberService;
    private final JwtService jwtService;

    public LoginController(MemberService memberService, JwtService jwtService) {
        this.memberService = memberService;
        this.jwtService = jwtService;
    }

    @RequestMapping(value="/")
    public String Test() {
        return "Test Page";
    }

    @RequestMapping(value ="/findbyid" ,method = RequestMethod.GET)
    public MemberVO FindByID(@RequestParam String ID) {
        MemberVO result;
        try {
            result = memberService.findByID(ID).get(0);
            return result;
        }catch(Exception e) {

        }
        return null;
    }

    @RequestMapping(value ="/findbyemail", method = RequestMethod.GET)
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

    @RequestMapping(value ="/makeaccount", method = RequestMethod.POST)
    public ResponseEntity<SendMessage<MemberVO>> MakeAccount(MemberVO memberVO) {
        SendMessage<MemberVO> message = null;
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        try{
            memberVO.CheckValidate();
            memberVO.setIsdeleted(Byte.parseByte("1"));
            memberService.MakeAccount(memberVO);
        }catch(NullPointerException e){
            message= new SendMessage<>(null,StatusEnum.BAD_REQUEST,e.getMessage());
            return new ResponseEntity<>(message,headers,HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            message= new SendMessage<>(null,StatusEnum.INTERNAL_SERVER_ERROOR,e.getMessage());
            return new ResponseEntity<>(message,headers,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        message=new SendMessage<>(memberVO,StatusEnum.OK,"OK");
        return new ResponseEntity<>(message,headers,HttpStatus.OK);
    }
    @RequestMapping(value = "/loginaction", method = RequestMethod.GET)
    public ResponseEntity<SendMessage<String>> LoginAction(@CookieValue(value="jwttoken",defaultValue = "",required = true) String jwt,
                                                                        LoginRequestVO loginRequestVO,
                                                                        HttpServletResponse response){

        MemberVO memberVO;
        SendMessage<String> message = null;
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        String token = null;
        Cookie cookie= null;
        if(loginRequestVO.getUserID()==null || loginRequestVO.getPassword()==null || loginRequestVO.getAutologin() ==null){
            message= new SendMessage<>(null,StatusEnum.BAD_REQUEST,"parameter error");
            return new ResponseEntity<>(message,headers,HttpStatus.BAD_REQUEST);
        }
        if(!jwt.equals("")){//토큰이있음
            Map<String,Object> map = jwtService.getUserID(jwt);
            if(map==null){
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




        }
        cookie.setPath("/");
        response.addCookie(cookie);
        message=new SendMessage<>(token,StatusEnum.OK,"OK");
        return new ResponseEntity<>(message,headers,HttpStatus.OK);
    }


    /*@RequestMapping(value="/testjwt")
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
    }*/
}
