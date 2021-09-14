package com.example.demo.controller;

import com.example.demo.JWT.JwtService;
import com.example.demo.SendMessage.SendMessage;
import com.example.demo.dao.MemberService;
import com.example.demo.vo.Enum.StatusEnum;
import com.example.demo.dto.Member.CheckDuplicateEmailRequest;
import com.example.demo.vo.Member.MemberVO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.Map;

@RestController
@RequestMapping(value="/api/makeaccount")
public class MakeAccountController {

    private final MemberService memberService;

    private final JwtService jwtService;

    public MakeAccountController(MemberService memberService, JwtService jwtService){
        this.memberService = memberService;
        this.jwtService = jwtService;
    }

    @RequestMapping(value="/checkduplicateid", method= RequestMethod.GET)
    public ResponseEntity<SendMessage<Boolean>> CheckDuplicateID(String ID){
        SendMessage<Boolean> message = null;
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        try{
            if(!memberService.isDuplicated(ID)){
                message= new SendMessage<>(false,StatusEnum.OK,"중복된 ID입니다. 다른 ID를 사용해주세요");
            }else{
                message = new SendMessage<>(true,StatusEnum.OK,"OK");
            }
        }catch(Exception e){
            message= new SendMessage<>(false, StatusEnum.INTERNAL_SERVER_ERROOR,e.getMessage());
            return new ResponseEntity<>(message,headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(message,headers,HttpStatus.OK);


    }


    @RequestMapping(value="/checkduplicatedemail",method = RequestMethod.GET)
    public ResponseEntity<SendMessage<Boolean>> CheckDuplicateEmail(CheckDuplicateEmailRequest email){
        SendMessage<Boolean> message = null;
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        try{
            email.IsValidateEmail();
            if(!memberService.isDuplicatedEmail(email.getEmail())){
                message= new SendMessage<>(false,StatusEnum.OK,"중복된 ID입니다. 다른 ID를 사용해주세요");
            }else{
                message = new SendMessage<>(true,StatusEnum.OK,"OK");
            }
        }catch(Exception e){
            message= new SendMessage<>(false, StatusEnum.INTERNAL_SERVER_ERROOR,e.getMessage());
            return new ResponseEntity<>(message,headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(message,headers,HttpStatus.OK);
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

    @RequestMapping(value="/updateaccount",method=RequestMethod.PATCH)
    public ResponseEntity<SendMessage<MemberVO>> UpdateAccount(HttpServletRequest request ,MemberVO memberVO){
        SendMessage<MemberVO> message = null;
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> auth;
        try{
            auth=jwtService.requestAuthorization(request);
            memberVO.CheckValidate();
            memberService.UpdateAccount(memberVO);
        }catch(IllegalAccessException e){
            message = new SendMessage<>(null,StatusEnum.UNAUTHORIZED,e.getMessage());
            return new ResponseEntity<>(message,headers,HttpStatus.UNAUTHORIZED);
        }catch(NullPointerException e){
            message=new SendMessage<>(null,StatusEnum.BAD_REQUEST,e.getMessage());
            return new ResponseEntity<>(message,headers,HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            message=new SendMessage<>(null,StatusEnum.INTERNAL_SERVER_ERROOR,e.getMessage());
            return new ResponseEntity<>(message,headers,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        message = new SendMessage<>(memberVO,StatusEnum.OK,"OK");
        return new ResponseEntity<>(message,headers,HttpStatus.OK);

    }

    /*@RequestMapping(value="/deleteaccount", method=RequestMethod.PUT)
    public ResponseEntity<SendMessage<Boolean>> DeleteAccount(HttpServletRequest request){

    }*/
}
