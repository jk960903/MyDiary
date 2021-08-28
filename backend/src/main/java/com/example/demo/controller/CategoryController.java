package com.example.demo.controller;

import com.example.demo.JWT.JwtService;
import com.example.demo.dao.CategoryService;
import com.example.demo.dto.Category.AddCategoryRequest;
import com.example.demo.dto.Category.DeleteCategoryRequest;
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
    private final JwtService jwtService;

    @Autowired(required = true)
    private final CategoryService categoryService;

    public CategoryController(JwtService jwtService, CategoryService categoryService) {
        this.jwtService = jwtService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value="/getcategoryaction")
    public ResponseEntity<SendMessage<List<CategoryVO>>> GetCategoryList(HttpServletRequest request){
        //로그인 되어있는지 확인
        Map<String,Object> auth;
        SendMessage<List<CategoryVO>> sendMessage;
        //헤더설정 객체
        HttpHeaders headers = new HttpHeaders();
        //회원에 대한 category list 조회
        List<CategoryVO> categoryVOList = new ArrayList<CategoryVO>();
        //헤더 설정
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        try{
            auth = jwtService.requestAuthorization(request);
            categoryVOList=categoryService.getCategoryList(Long.parseLong((String)auth.get("idx")));

        }catch(IllegalAccessException e){
            sendMessage = new SendMessage<>(null,StatusEnum.UNAUTHORIZED,e.getMessage());
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.UNAUTHORIZED);
        }catch(Exception e){
            sendMessage=new SendMessage<>(null,StatusEnum.INTERNAL_SERVER_ERROOR,e.getMessage());
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.UNAUTHORIZED);
        }
        sendMessage=new SendMessage<>(categoryVOList,StatusEnum.OK,"OK");
        return new ResponseEntity<>(sendMessage,headers,HttpStatus.OK);



    }
    @RequestMapping(value ="/addcategory", method = RequestMethod.POST)
    public ResponseEntity<SendMessage<CategoryVO>> AddCategory(HttpServletRequest request, int value , AddCategoryRequest addcategory){
        Map<String,Object> auth;
        SendMessage<CategoryVO> sendMessage=null;
        HttpHeaders headers = new HttpHeaders();
        CategoryVO categoryVO;
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        try{
            auth= jwtService.requestAuthorization(request);
            addcategory.setMemberidx(Long.parseLong((String)auth.get("idx")));
            categoryVO = CategoryVO.builder().memberidx(addcategory.getMemberidx())
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
    public ResponseEntity<SendMessage<Boolean>> DeleteCategory(HttpServletRequest request , DeleteCategoryRequest deleteCategoryRequest){
        Map<String,Object> auth;
        SendMessage<Boolean> sendMessage=null;
        HttpHeaders headers = new HttpHeaders();
        CategoryVO deleteCategory=null;
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        try{
            auth = jwtService.requestAuthorization(request);
            jwtService.isValidateRequest(deleteCategoryRequest.getMemberIdx(),Long.parseLong((String)auth.get("idx")));
            deleteCategory = categoryService.findCategoryByIdx(deleteCategory.getIdx());
            categoryService.DeleteCategory(deleteCategoryRequest);

        }catch(IllegalAccessException e){
            sendMessage = new SendMessage<>(null,StatusEnum.UNAUTHORIZED,"token expired");
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
        sendMessage = new SendMessage<>(true,StatusEnum.OK,"OK");
        return new ResponseEntity<>(sendMessage,headers,HttpStatus.OK);

    }


}
