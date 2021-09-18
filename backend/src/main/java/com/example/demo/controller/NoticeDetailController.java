package com.example.demo.controller;

import com.example.demo.JWT.JwtService;
import com.example.demo.dao.NoticeDetailService;
import com.example.demo.dao.NoticeReviewReviewService;
import com.example.demo.dao.NoticeReviewService;
import com.example.demo.dao.NoticeService;
import com.example.demo.dto.Notice.DeleteNoticeDetailRequest;
import com.example.demo.dto.Notice.UpdateNoticeDetailRequest;
import com.example.demo.vo.Enum.StatusEnum;
import com.example.demo.SendMessage.SendMessage;
import com.example.demo.vo.notice.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="api/noticedetail")
public class NoticeDetailController {

    private final NoticeDetailService noticeDetailService;


    private final JwtService jwtService;

    private final NoticeService noticeService;

    private final NoticeReviewService noticeReviewService;

    private final NoticeReviewReviewService noticeReviewReviewService;


    public NoticeDetailController(NoticeDetailService noticeDetailService, JwtService jwtService, NoticeService noticeService, NoticeReviewService noticeReviewService, NoticeReviewReviewService noticeReviewReviewService) {
        this.noticeDetailService = noticeDetailService;
        this.jwtService = jwtService;
        this.noticeService = noticeService;
        this.noticeReviewService = noticeReviewService;
        this.noticeReviewReviewService = noticeReviewReviewService;
    }

    @RequestMapping(value = "/getnoticedetail", method = RequestMethod.GET )
    public ResponseEntity<SendMessage<NoticeResultVO>> GetNoticeDetail(HttpServletRequest request , Long notice_idx){
        SendMessage<NoticeResultVO> sendMessage = null;
        NoticeDetailVO noticeDetailVO = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        NoticeResultVO noticeResultVO = null;
        NoticeVO noticeVO = null;
        List<NoticeReviewVO> noticeReviewVO;
        List<NoticeReviewResult> noticeReviewResults = new ArrayList<>();
        try{
            noticeVO = noticeService.GetNoticeData(notice_idx);
            noticeDetailVO = noticeDetailService.GetNoticeDetail(notice_idx);
            noticeReviewVO = noticeReviewService.getNoticeReviewList(notice_idx);
            for(int i = 0 ; i<noticeReviewVO.size(); i++){
                List<NoticeReviewReviewVO> noticeReviewReviewVOList = noticeReviewReviewService.GetNoticeReviewReviewList(noticeReviewVO.get(i).getIdx());
                noticeReviewResults.add(new NoticeReviewResult(noticeReviewVO.get(i),noticeReviewReviewVOList));
            }
        }catch(IndexOutOfBoundsException e ){
            sendMessage = new SendMessage<>(null,StatusEnum.BAD_REQUEST,e.getMessage());
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.BAD_REQUEST);
        }catch(NullPointerException e){
            sendMessage = new SendMessage<>(null,StatusEnum.BAD_REQUEST,e.getMessage());
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.BAD_REQUEST);
        } catch(Exception e){// 해당 목표를 못찾음
            sendMessage = new SendMessage<>(null,StatusEnum.INTERNAL_SERVER_ERROOR,e.getMessage());
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        noticeResultVO = new NoticeResultVO(noticeVO,noticeDetailVO,noticeReviewResults);
        sendMessage = new SendMessage<>(noticeResultVO,StatusEnum.OK,"OK");
        return new ResponseEntity<>(sendMessage,headers,HttpStatus.OK);


    }

    @RequestMapping(value="/updatenoticedetail",method=RequestMethod.PATCH)
    public ResponseEntity<SendMessage<NoticeDetailVO>> UpdateNoticeDetail(HttpServletRequest request, UpdateNoticeDetailRequest noticeDetailRequest){
        Map<String,Object> auth;
        SendMessage<NoticeDetailVO> sendMessage=null;
        HttpHeaders headers = new HttpHeaders();
        NoticeDetailVO noticeDetailVO=null;
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        try{
            noticeDetailRequest.CheckValidate();
            noticeDetailService.UpdateNoticeDetail(noticeDetailRequest);
        }catch(NullPointerException e){
            sendMessage = new SendMessage<>(null,StatusEnum.BAD_REQUEST,e.getMessage());
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            sendMessage = new SendMessage<>(null,StatusEnum.INTERNAL_SERVER_ERROOR,e.getMessage());
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        sendMessage = new SendMessage<>(noticeDetailVO,StatusEnum.OK,"OK");
        return new ResponseEntity<>(sendMessage,headers,HttpStatus.OK);
    }

    @RequestMapping(value="/deletenotice")
    public ResponseEntity<SendMessage<Integer>> DeleteNoticeDetail(HttpServletRequest request, DeleteNoticeDetailRequest deleteNoticeDetailRequest){
        Map<String,Object> auth;
        SendMessage<Integer> sendMessage=null;
        HttpHeaders headers = new HttpHeaders();
        NoticeDetailVO noticeDetailVO=null;
        try{
            deleteNoticeDetailRequest.CheckValidate();
            noticeDetailVO=noticeDetailService.GetNoticeDetail(deleteNoticeDetailRequest.getIdx());
            noticeService.DeleteNotice(noticeDetailVO.getNoticeidx());
            noticeDetailService.DeleteNoticeDetail(noticeDetailVO);
        }catch(NullPointerException e){
            sendMessage = new SendMessage<>(0,StatusEnum.BAD_REQUEST,e.getMessage());
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.BAD_REQUEST);
        }catch(IndexOutOfBoundsException e){
            sendMessage =new SendMessage<>(0,StatusEnum.BAD_REQUEST,e.getMessage());
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            sendMessage = new SendMessage<>(0,StatusEnum.BAD_REQUEST,e.getMessage());
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        sendMessage= new SendMessage<>(1,StatusEnum.OK,"OK");
        return new ResponseEntity<>(sendMessage,headers,HttpStatus.OK);
    }

}
