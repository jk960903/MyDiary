package com.example.demo.controller;


import com.example.demo.JWT.JwtService;
import com.example.demo.dao.NoticeReviewReviewService;
import com.example.demo.dto.Notice.DeleteNoticeReviewReviewRequest;
import com.example.demo.dto.Notice.UpdateNoticeReviewReviewRequest;
import com.example.demo.vo.Enum.StatusEnum;
import com.example.demo.SendMessage.SendMessage;
import com.example.demo.vo.notice.NoticeReviewReviewVO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/notice-review-review")
public class NoticeReview_ReviewController {


    private final NoticeReviewReviewService noticeReviewReviewService;


    private final JwtService jwtService;

    public NoticeReview_ReviewController(NoticeReviewReviewService noticeReviewReviewService, JwtService jwtService) {
        this.noticeReviewReviewService = noticeReviewReviewService;
        this.jwtService = jwtService;
    }

    @RequestMapping(value="/addnotice_review_review")
    public ResponseEntity<SendMessage<NoticeReviewReviewVO>> AddNoticeReviewReview(HttpServletRequest request,NoticeReviewReviewVO noticeReviewReviewVO){
        Map<String,Object> auth;
        SendMessage<NoticeReviewReviewVO> message;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));
        try{
            auth=jwtService.requestAuthorization(request);
            noticeReviewReviewVO.setMemberidx(Long.parseLong(((String)auth.get("memberidx"))));
            noticeReviewReviewVO.CheckLoginValidate(Long.parseLong((String)auth.get("idx")));
            noticeReviewReviewVO.CheckValidate();
            noticeReviewReviewService.AddNoticeReviewReview(noticeReviewReviewVO);
        }catch(IllegalAccessException e){//토큰 만료 및 로그인 안되어있을때
            message = new SendMessage<>(null,StatusEnum.UNAUTHORIZED,e.getMessage());
            return new ResponseEntity<>(message,headers,HttpStatus.UNAUTHORIZED);
        }catch(NullPointerException e){// 파라미터 값 이상
            message = new SendMessage<>(null, StatusEnum.BAD_REQUEST,e.getMessage());
            return new ResponseEntity<>(message,headers,HttpStatus.BAD_REQUEST);
        }catch(Exception e) {
            message = new SendMessage<>(null, StatusEnum.INTERNAL_SERVER_ERROOR, e.getMessage());
            return new ResponseEntity<>(message, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        message = new SendMessage<>(noticeReviewReviewVO,StatusEnum.OK,"OK");
        return new ResponseEntity<>(message,headers,HttpStatus.OK);
    }

    @RequestMapping(value="/updatenotice-review-reivew")
    public ResponseEntity<SendMessage<NoticeReviewReviewVO>> UpdateNoticeReviewReviewVO(HttpServletRequest request, UpdateNoticeReviewReviewRequest updateRequest){
        Map<String,Object> auth;
        SendMessage<NoticeReviewReviewVO> message;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));
        NoticeReviewReviewVO noticeReviewReviewVO;
        try{
            auth = jwtService.requestAuthorization(request);
            updateRequest.CheckLoginValidate(Long.parseLong((String)auth.get("idx")));
            updateRequest.CheckValidate();
            noticeReviewReviewVO=noticeReviewReviewService.UpdateNoticeReviewReview(updateRequest);
        }catch(IllegalAccessException e){//토큰 만료 및 로그인 안되어있을때
            message = new SendMessage<>(null,StatusEnum.UNAUTHORIZED,e.getMessage());
            return new ResponseEntity<>(message,headers,HttpStatus.UNAUTHORIZED);
        }catch(NullPointerException e){// 파라미터 값 이상
            message = new SendMessage<>(null, StatusEnum.BAD_REQUEST,e.getMessage());
            return new ResponseEntity<>(message,headers,HttpStatus.BAD_REQUEST);
        }catch(Exception e) {
            message = new SendMessage<>(null, StatusEnum.INTERNAL_SERVER_ERROOR, e.getMessage());
            return new ResponseEntity<>(message, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        message= new SendMessage<>(noticeReviewReviewVO,StatusEnum.OK,"OK");
        return new ResponseEntity<>(message,headers,HttpStatus.OK);
    }

    @RequestMapping(value="/deletenotice-review-review")
    public ResponseEntity<SendMessage<NoticeReviewReviewVO>> DeleteNoticeReviewReviewVO(HttpServletRequest request, DeleteNoticeReviewReviewRequest deleteNoticeReviewReviewRequest){
        Map<String,Object> auth;
        SendMessage<NoticeReviewReviewVO> message;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));
        NoticeReviewReviewVO findnoticeReviewReviewVO;
        try{
            auth = jwtService.requestAuthorization(request);
            noticeReviewReviewService.DeleteNoticeReviewReview(deleteNoticeReviewReviewRequest.getIdx());

        }catch(IllegalAccessException e){
            message = new SendMessage<>(null,StatusEnum.UNAUTHORIZED,e.getMessage());
            return new ResponseEntity<>(message,headers,HttpStatus.UNAUTHORIZED);
        }catch(IndexOutOfBoundsException e){

        }catch(Exception e){

        }

        return null;
    }
}
