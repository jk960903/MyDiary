package com.example.demo.Controllers;

import com.example.demo.JWT.JwtService;
import com.example.demo.SendMessage.SendMessage;
import com.example.demo.controller.LoginController;
import com.example.demo.dao.MemberService;
import com.example.demo.vo.Enum.StatusEnum;
import com.example.demo.vo.Member.MemberVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers = LoginController.class)
public class LogintControllerTest {

    private final MockMvc mvc;

    private final MemberService memberService;

    private final TestRestTemplate restTemplate;



    private final JwtService jwtService;
    public LogintControllerTest(MockMvc mvc, MemberService memberService, TestRestTemplate restTemplate, JwtService jwtService){
        this.mvc=mvc;
        this.memberService = memberService;
        this.restTemplate = restTemplate;
        this.jwtService = jwtService;

    }


    @Test
    public void FindByIDTest() throws Exception{
        String ID = "1234";
        String Email = "1234";
        MemberVO memberVO = MemberVO.builder().ID(ID).name("1234")
                .pwd("1234").email("1234").phone("1234").sex(1).isdeleted(0).idx((long)1).build();
        MemberVO findMember=memberService.findByID(ID).get(0);
        SendMessage<MemberVO> sendMessage = new SendMessage<>(memberVO, StatusEnum.OK,"OK");
        String url = "localhost:8080/api/login/findbyid";

        ResponseEntity<SendMessage<MemberVO>> responseEntity;
        //responseEntity = restTemplate.getFo;
    }
}
/*
* @RunWith
* 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자 실행
* Spring Boot Test와 JUnit사이 연결자 역할
*
* @WebMvcTest
* Web에 집중할수 있는 어노테이션
* @Controller , @ControllerAdvice등 사용 가능
* @Service @Component,@Repository  사용 불가
*
* given when then에 대한 것을 지켜야한다.
* 어떤값이 주어지고 무엇을 했을때 어떤값을 원한다
*
* 모든 response에대한 테스트 진행
*
* Fast ,Independent , Repeatable , Self-validatatin , Timely
*
*
* */