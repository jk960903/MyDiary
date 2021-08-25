package com.example.demo.controller;

import com.example.demo.JWT.JwtService;
import com.example.demo.dao.CategoryService;
import com.example.demo.vo.Category.CategoryVO;
import com.example.demo.vo.Enum.StatusEnum;
import com.example.demo.SendMessage.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/api/category")
public class CategoryController {
    @Autowired(required = true)
    private JwtService jwtService;

    @Autowired(required = true)
    private CategoryService categoryService;

    /*@RequestMapping(value ="/getcategory")
    public ModelAndView GetCategoryView(HttpServletRequest request){
        Map<String, Object> map = jwtService.requestAuthorization(request);
        if(map == null){
            //로그인 실패
            return new ModelAndView("Login");
        }
        else{

        }
        return new ModelAndView("");
    }*/
    @RequestMapping(value="/getcategoryaction")
    public ResponseEntity<SendMessage<List<CategoryVO>>> GetCategoryAction(HttpServletRequest request){
        //로그인 되어있는지 확인
        Map<String,Object> map;
        SendMessage<List<CategoryVO>> sendMessage;
        //헤더설정 객체
        HttpHeaders headers = new HttpHeaders();
        //회원에 대한 category list 조회
        List<CategoryVO> categoryVOList = new ArrayList<CategoryVO>();
        //헤더 설정
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        try{
            map =jwtService.requestAuthorization(request);
        }catch(IllegalAccessException e){//토큰이 유효하지 않거나 유효시간 경과
            //토큰이 유효하여 해당 멤버에 대한 선택 카테고리 종목 가져오기
            sendMessage = new SendMessage<List<CategoryVO>>(null,StatusEnum.UNAUTHORIZED,"token expired");
            //return
            return new ResponseEntity<>(sendMessage,headers, HttpStatus.UNAUTHORIZED);
        }
        categoryVOList = categoryService.getCategoryList(Long.valueOf(String.valueOf(map.get("idx"))));
        //성공 설정
        sendMessage = new SendMessage<List<CategoryVO>>(categoryVOList,StatusEnum.OK,"success");
        return new ResponseEntity<>(sendMessage,headers,HttpStatus.OK);



    }
    @RequestMapping(value ="/addcategory", method = RequestMethod.POST)
    public ResponseEntity<SendMessage<CategoryVO>> AddCategory(HttpServletRequest request,int value ,Long member_seq){
        Map<String,Object> auth;
        SendMessage<CategoryVO> sendMessage=null;
        HttpHeaders headers = new HttpHeaders();
        CategoryVO categoryVO;
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        try{
            Map<String ,Object> member= jwtService.requestAuthorization(request);
            if(Long.parseLong((String)member.get("idx"))!=member_seq){
                throw new IllegalArgumentException();
            }
            if(value <= 0 ) {
                sendMessage = new SendMessage<>(null,StatusEnum.BAD_REQUEST,"BAD REQUEST");
                return new ResponseEntity<>(null,headers,HttpStatus.BAD_REQUEST);
            }
            categoryVO = CategoryVO.builder().idx(Long.valueOf(String.valueOf(member.get("idx"))))
                    .category(Byte.valueOf((String.valueOf(value))))
                    .legdate(null)
                    .isdeleted(Byte.valueOf((String.valueOf("1")))).build();
            categoryService.AddCategory(categoryVO);
        }catch(IllegalAccessException e){
            sendMessage = new SendMessage<CategoryVO>(null,StatusEnum.UNAUTHORIZED,"token expired");
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.UNAUTHORIZED);
        }catch(Exception e) {
            sendMessage = new SendMessage<>(null, StatusEnum.INTERNAL_SERVER_ERROOR, e.getMessage());
            return new ResponseEntity<>(sendMessage, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        sendMessage=new SendMessage<>(categoryVO,StatusEnum.OK,"OK");
        return new ResponseEntity<>(sendMessage,headers,HttpStatus.OK);
    }
    @RequestMapping(value = "/deletecategory")
    public ResponseEntity<SendMessage<CategoryVO>> DeleteCategory(HttpServletRequest request , Long Seq,Long member_seq){
        Map<String,Object> auth;
        SendMessage<CategoryVO> sendMessage=null;
        HttpHeaders headers = new HttpHeaders();
        CategoryVO deleteCategory;
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        try{
            auth = jwtService.requestAuthorization(request);
            if(Long.parseLong((String)auth.get("idx"))!=member_seq){
                throw new IllegalArgumentException();
            }
            if(Seq <= 0 ){
                throw new NullPointerException("BAD REQUEST");
            }
            deleteCategory = categoryService.DeleteCategory(Seq);
            if(deleteCategory == null){
                sendMessage = new SendMessage<CategoryVO>(deleteCategory,StatusEnum.BAD_REQUEST,"해당 카테고리가 없습니다.");
                return new ResponseEntity<>(sendMessage,headers,HttpStatus.BAD_REQUEST);
            }
        }catch(IllegalAccessException e){
            sendMessage = new SendMessage<CategoryVO>(null,StatusEnum.UNAUTHORIZED,"token expired");
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.UNAUTHORIZED);
        }catch(NullPointerException e){
            sendMessage=new SendMessage<>(null,StatusEnum.BAD_REQUEST,e.getMessage());
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.BAD_REQUEST);
        }catch(IndexOutOfBoundsException e){
            sendMessage=new SendMessage<>(null,StatusEnum.BAD_REQUEST,e.getMessage());
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            sendMessage=new SendMessage<>(null,StatusEnum.INTERNAL_SERVER_ERROOR,e.getMessage());
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        sendMessage = new SendMessage<CategoryVO>(deleteCategory,StatusEnum.OK,"OK");
        return new ResponseEntity<SendMessage<CategoryVO>>(sendMessage,headers,HttpStatus.OK);

    }


}
