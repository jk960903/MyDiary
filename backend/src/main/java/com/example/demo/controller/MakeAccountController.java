package com.example.demo.controller;

import com.example.demo.SendMessage.SendMessage;
import com.example.demo.dao.MemberService;
import com.example.demo.vo.Enum.StatusEnum;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.charset.Charset;

@RestController
@RequestMapping(value="/api/makeaccount")
public class MakeAccountController {

    private final MemberService memberService;

    public MakeAccountController(MemberService memberService){
        this.memberService = memberService;
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
    public ResponseEntity<SendMessage<Boolean>> CheckDuplicateEmail(String email){
        SendMessage<Boolean> message = null;
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        try{
            if(!memberService.isDuplicatedEmail(email)){
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
}
