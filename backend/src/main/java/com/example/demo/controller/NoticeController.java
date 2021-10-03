package com.example.demo.controller;

import com.example.demo.JWT.JwtService;
import com.example.demo.dao.NoticeDetailService;
import com.example.demo.dao.NoticeReviewReviewService;
import com.example.demo.dao.NoticeReviewService;
import com.example.demo.dao.NoticeService;
import com.example.demo.dto.Notice.UpdateNoticeCountRequest;
import com.example.demo.vo.Enum.StatusEnum;
import com.example.demo.vo.NoticeRequest;
import com.example.demo.SendMessage.SendMessage;
import com.example.demo.vo.notice.NoticeDetailVO;
import com.example.demo.vo.notice.NoticeVO;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="api/notice")
@NoArgsConstructor
public class NoticeController {
    private  Logger logger = LoggerFactory.getLogger(this.getClass());

    private  NoticeService noticeService;

    private JwtService jwtService;

    private  NoticeDetailService noticeDetailService;

    @Autowired
    public NoticeController(NoticeService noticeService, JwtService jwtService , NoticeDetailService noticeDetailService,
                            NoticeReviewService noticeReviewService, NoticeReviewReviewService noticeReviewReviewService){
        this.noticeService = noticeService;
        this.jwtService=jwtService;
        this.noticeDetailService =noticeDetailService;
    }
    //테스트 완
    @GetMapping(value="/noticeget")
    public ResponseEntity<SendMessage<List<NoticeVO>>> NoticeGet(NoticeRequest request, HttpServletRequest servletRequest){
        SendMessage<List<NoticeVO>> sendMessage;
        List<NoticeVO> list=null;
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        try{
            if(request.getSearch()==null||request.getSearch().equals("")) {
                list=noticeService.GetNoticeList();
            }else {
                list=noticeService.GetSearchNoticeList(request.getSearch());
            }
        }catch(Exception e){
            sendMessage=new SendMessage<>(null,StatusEnum.INTERNAL_SERVER_ERROOR,"INTERNAL SERVER ERROR");
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        sendMessage=new SendMessage<>(list,StatusEnum.OK,"OK");
        return new ResponseEntity<>(sendMessage,headers,HttpStatus.OK);
    }

    /*
    공지사항 추가 api

     */
    @PostMapping(value="/addnotice")
    public ResponseEntity<SendMessage<NoticeVO>> AddNotice(NoticeVO noticeVO,HttpServletRequest request,String Content){
        Map<String,Object> auth;
        SendMessage<NoticeVO> message;
        HttpHeaders headers = new HttpHeaders();
        NoticeVO noticeResult=null;
        NoticeDetailVO noticeDetailVO =null;
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        try{
            noticeVO.CheckValidate();
            noticeVO.setRegdate(noticeVO.SetToday());
            noticeResult =noticeService.AddNotice(noticeVO);
            noticeDetailVO = noticeDetailVO.builder().noticeidx(noticeResult.getIdx()).content(Content).isdeleted(1).imageurl("").build();
            noticeDetailService.AddNoticeDetail(noticeDetailVO);
        } catch (IllegalAccessException e) {
            message= new SendMessage<>(null, StatusEnum.UNAUTHORIZED,e.getMessage());
            return new ResponseEntity<>(message,headers, HttpStatus.UNAUTHORIZED);
        }catch(NullPointerException e){
            message= new SendMessage<>(null,StatusEnum.BAD_REQUEST,e.getMessage());
            return new ResponseEntity<>(message,headers,HttpStatus.BAD_REQUEST);
        }
        catch(Exception e){
            message=new SendMessage<>(null,StatusEnum.INTERNAL_SERVER_ERROOR,e.getMessage());
            return new ResponseEntity<>(message,headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        message = new SendMessage<>(noticeVO,StatusEnum.OK,"OK");
        return new ResponseEntity<>(message,headers,HttpStatus.OK);

    }

    @PatchMapping(value="/updatenoticeview")
    public ResponseEntity<SendMessage<Integer>> UpdateNoticeCountView( UpdateNoticeCountRequest updateNoticeCountRequest){
        SendMessage<Integer> message;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> auth;
        NoticeVO noticeVO = null;
        try{
            if(updateNoticeCountRequest.checkValidate()){
                noticeVO=noticeService.GetNoticeViewCount(updateNoticeCountRequest.getIdx());
                updateNoticeCountRequest.setViewCount(noticeVO.getViewcount()+1);
                noticeService.UpdateNotoiceView(updateNoticeCountRequest);
            }
        }catch(NullPointerException e){
            message =new SendMessage<>(null,StatusEnum.BAD_REQUEST,e.getMessage());
            return new ResponseEntity<>(message,headers,HttpStatus.BAD_REQUEST);
        }catch(IndexOutOfBoundsException e){
            message=new SendMessage<>(null,StatusEnum.BAD_REQUEST,e.getMessage());
            return new ResponseEntity<>(message,headers,HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            message= new SendMessage<>(null,StatusEnum.INTERNAL_SERVER_ERROOR,e.getMessage());
            return new ResponseEntity<>(message,headers,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        message = new SendMessage<>(noticeVO.getViewcount()+1,StatusEnum.OK,"OK");
        return new ResponseEntity<>(message,headers,HttpStatus.OK);
    }




}
