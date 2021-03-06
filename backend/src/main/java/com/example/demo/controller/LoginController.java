package com.example.demo.controller;

import com.example.demo.JWT.JwtService;
import com.example.demo.dto.Member.FindMemberEmailRequest;
import com.example.demo.vo.Enum.StatusEnum;
import com.example.demo.SendMessage.SendMessage;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import com.example.demo.vo.Member.MemberVO;
import com.example.demo.dao.MemberService;
import com.example.demo.vo.Login.LoginRequestVO;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(value="/api/login")
public class LoginController {

    private MemberService memberService;

    private JwtService jwtService;

    @Autowired
    public LoginController(MemberService memberService, JwtService jwtService) {
        this.memberService = memberService;
        this.jwtService = jwtService;
    }

    public LoginController(MemberService memberService){
        this.memberService = memberService;

    }



    //테스트완
    /*
    ID를 통해 계정 찾기
     */
    @GetMapping(value ="/findbyid")
    public ResponseEntity<SendMessage<MemberVO>> FindByID(@RequestParam String ID) {
        MemberVO result=null;
        List<MemberVO> list=null;
        SendMessage<MemberVO> message =null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        try {
            list = memberService.findByID(ID);
            System.out.println(list);

        }catch(IndexOutOfBoundsException e){
            message = new SendMessage<>(null,StatusEnum.NOT_FOUND,e.getMessage());

        }
        catch(Exception e) {
            message=new SendMessage<>(null,StatusEnum.INTERNAL_SERVER_ERROOR,e.getMessage());
            return new ResponseEntity<>(message,headers,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        message=new SendMessage<>(result,StatusEnum.OK,"OK");
        return new ResponseEntity<>(message,headers,HttpStatus.OK);
    }
    //테스트 완
    /*
    이메일을통한 계정 찾기
     */
    @GetMapping(value ="/findbyemail")
    public ResponseEntity<SendMessage<MemberVO>> FindByEmail(FindMemberEmailRequest email) {
        MemberVO result;
        SendMessage<MemberVO> message = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        try {
            if(email.IsValidateEmail()){
                result = memberService.findByEmail(email);
            }else{
                message= new SendMessage<>(null,StatusEnum.BAD_REQUEST,"정상적인 이메일 형식이 아닙니다.");
                return new ResponseEntity<>(message,headers,HttpStatus.BAD_REQUEST);
            }
        } catch (IndexOutOfBoundsException e) {
            message = new SendMessage<>(null, StatusEnum.NOT_FOUND, e.getMessage());
            return new ResponseEntity<>(message, headers, HttpStatus.NOT_FOUND);
        }catch(Exception e){
            message= new SendMessage<>(null,StatusEnum.INTERNAL_SERVER_ERROOR,e.getMessage());
            return new ResponseEntity<>(message,headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        message=new SendMessage<>(result,StatusEnum.OK,"OK");
        return new ResponseEntity<>(message,headers,HttpStatus.OK);
    }

    //테스트 완 쿠키 까지 테스트완
    /*
    로그인
    Params
    String userID;
    String password;
    String autologin;
     */
    @GetMapping(value = "/loginaction")
    public ResponseEntity<SendMessage<String>> LoginAction(@CookieValue(value="jwttoken",defaultValue = "",required = true) String jwt,
                                                                        LoginRequestVO loginRequestVO,
                                                                        HttpServletResponse response){

        MemberVO memberVO;
        SendMessage<String> message = null;
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        String token = null;
        Cookie cookie= null;
        try{
            if(!jwt.equals("")){
                Map<String,Object> map = jwtService.getUserID(jwt);
                message=new SendMessage<>(token,StatusEnum.OK,"OK");
                return new ResponseEntity<>(message,headers,HttpStatus.OK);
            }else{
                memberVO= memberService.Login(loginRequestVO);
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
            }
        }catch(ExpiredJwtException e){
            message = new SendMessage<>("",StatusEnum.UNAUTHORIZED,"토큰 만료로 인해 로그아웃"); //토큰이 만료되었을때 다시 재발급하는 거에대해서 고민해보기
            cookie = new Cookie("jwttoken","");
            cookie.setPath("/");

            response.addCookie(cookie);
            return new ResponseEntity<>(message,headers,HttpStatus.UNAUTHORIZED);
        }catch(NullPointerException e){
            message=new SendMessage<>(null,StatusEnum.BAD_REQUEST,e.getMessage());
            return new ResponseEntity<>(message,headers,HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            message= new SendMessage<>(null,StatusEnum.INTERNAL_SERVER_ERROOR,e.getMessage());
            return new ResponseEntity<>(message,headers,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        cookie.setPath("/");
        response.addCookie(cookie);
        message=new SendMessage<>(token,StatusEnum.OK,"OK");
        return new ResponseEntity<>(message,headers,HttpStatus.OK);
    }



}
