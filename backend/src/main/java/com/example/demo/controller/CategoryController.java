package com.example.demo.controller;

import com.example.demo.JWT.JwtService;
import com.example.demo.dao.CategoryService;
import com.example.demo.vo.CategoryVO;
import com.example.demo.vo.Enum.StatusEnum;
import com.example.demo.vo.MemberVO;
import com.example.demo.vo.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/Category")
public class CategoryController {
    @Autowired(required = true)
    private JwtService jwtService;

    @Autowired(required = true)
    private CategoryService categoryService;

    @RequestMapping(value ="/GetCategory")
    public ModelAndView GetCategoryView(HttpServletRequest request){
        Map<String, Object> map = jwtService.requestAuthorization(request);
        if(map == null){
            //로그인 실패
            return new ModelAndView("Login");
        }
        else{

        }
        return new ModelAndView("");
    }
    @RequestMapping(value="/GetCategoryAction")
    public ResponseEntity<SendMessage<List<CategoryVO>>> GetCategoryAction(HttpServletRequest request){
        //로그인 되어있는지 확인
        Map<String,Object> map =jwtService.requestAuthorization(request);
        //return 에 들어갈 메시지
        SendMessage<List<CategoryVO>> sendMessage;
        //헤더설정 객체
        HttpHeaders headers = new HttpHeaders();
        //회원에 대한 category list 조회
        List<CategoryVO> categoryVOList = new ArrayList<CategoryVO>();

        //헤더 설정
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        //토큰이 유효하지 않거나 유효시간 경과
        if(map == null) {
            sendMessage = new SendMessage<List<CategoryVO>>(null,StatusEnum.UNAUTHORIZED,"token expired");
            //return
            return new ResponseEntity<>(sendMessage,headers, HttpStatus.UNAUTHORIZED);
        }
        //토큰이 유효하여 해당 멤버에 대한 선택 카테고리 종목 가져오기
        categoryVOList = categoryService.getCategoryList(Long.valueOf(String.valueOf(map.get("idx"))));
        //성공 설정
        sendMessage = new SendMessage<List<CategoryVO>>(categoryVOList,StatusEnum.OK,"success");
        return new ResponseEntity<>(sendMessage,headers,HttpStatus.OK);



    }
}
