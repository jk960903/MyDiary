package com.example.demo.controller;

import com.example.demo.dao.NoticeDetailService;
import com.example.demo.vo.Enum.StatusEnum;
import com.example.demo.vo.SendMessage;
import com.example.demo.vo.notice.NoticeDetailVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequestMapping(value="/NoticeDetail")
public class NoticeDetailController {
    @Autowired(required = true)
    NoticeDetailService noticeDetailService;

    @RequestMapping(value = "/GetNoticeDetail", method = RequestMethod.GET )
    public ResponseEntity<SendMessage<List<NoticeDetailVO>>> GetNoticeDetail(HttpServletRequest request , Long notice_idx){
        //공지사항 관련은 로그인 하지 않아도 가능 할수 있도록 설계
        SendMessage<List<NoticeDetailVO>> sendMessage = null;
        List<NoticeDetailVO> noticeDetailVO = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        if(notice_idx < 0 ){
            sendMessage = new SendMessage<>(noticeDetailVO,StatusEnum.BAD_REQUEST,"BAD REQUEST");
            return new ResponseEntity<>(sendMessage,headers, HttpStatus.BAD_REQUEST);
        }
        try{
           noticeDetailVO = noticeDetailService.GetNoticeDetail(notice_idx);
           if(noticeDetailVO.size() <= 0) throw new Exception("BAD REQUEST");
           sendMessage = new SendMessage<>(noticeDetailVO,StatusEnum.OK,"OK");

        }catch(Exception e){// 해당 목표를 못찾음
            sendMessage = new SendMessage<>(noticeDetailVO,StatusEnum.BAD_REQUEST,e.getMessage());
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(sendMessage,headers,HttpStatus.OK);


    }
    @RequestMapping(value = "/AddNoticeDetail", method=RequestMethod.POST)
    public ResponseEntity<SendMessage<Integer>> AddNoticeDetail(HttpServletRequest request, NoticeDetailVO noticeDetailVO){
        SendMessage<Integer> sendMessage = new SendMessage<>(1,StatusEnum.OK,"OK");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));
        return new ResponseEntity<>(sendMessage,headers,HttpStatus.OK);
    }


}
