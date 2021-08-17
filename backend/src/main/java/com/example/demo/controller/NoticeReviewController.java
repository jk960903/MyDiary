package com.example.demo.controller;

import com.example.demo.JWT.JwtService;
import com.example.demo.dao.NoticeReviewRepository;
import com.example.demo.dao.NoticeReviewService;
import com.example.demo.dao.NoticeService;
import com.example.demo.vo.Category.CategoryVO;
import com.example.demo.vo.Enum.StatusEnum;
import com.example.demo.vo.SendMessage;
import com.example.demo.vo.notice.ChangeNoticeReviewVO;
import com.example.demo.vo.notice.NoticeReviewReqeust;
import com.example.demo.vo.notice.NoticeReviewVO;
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
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/noticereview")
public class NoticeReviewController {
    @Autowired(required = true)
    private NoticeService noticeService;

    @Autowired(required = true)
    private NoticeReviewService noticeReviewService;
    @Autowired(required = true)
    private JwtService jwtService;

    @RequestMapping(value = "/addnoticereview", method = RequestMethod.POST)
    public ResponseEntity<SendMessage<Integer>> AddNoticeReview(HttpServletRequest request, NoticeReviewReqeust model){

        Map<String, Object> auth= jwtService.requestAuthorization(request);
        SendMessage<Integer> sendMessage=null;
        HttpHeaders headers = new HttpHeaders();
        NoticeReviewVO noticeReviewVO=null;
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        if(model.getNotice_idx() < 0 || model.getMember_idx()  < 0 ) return new ResponseEntity<>(null,headers, HttpStatus.BAD_REQUEST);
        if(auth == null){
            sendMessage = new SendMessage<Integer>(401, StatusEnum.UNAUTHORIZED,"Token Expired");
            return new ResponseEntity<SendMessage<Integer>>(sendMessage , headers, HttpStatus.UNAUTHORIZED);
        }
        noticeReviewVO = NoticeReviewVO.builder().noticeidx(model.getNotice_idx()).content(model.getContent())
                .memberidx(model.getMember_idx()).isDeleted(Byte.parseByte("1")).build();
        int queryresult = noticeReviewService.AddNoticeReview(noticeReviewVO);
        if(queryresult <0) {
            sendMessage = new SendMessage<Integer>(500,StatusEnum.INTERNAL_SERVER_ERROOR,"Intever ServerError");
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.UNAUTHORIZED);
        }
        sendMessage = new SendMessage<>(200,StatusEnum.OK,"OK");
        return new ResponseEntity<SendMessage<Integer>>(sendMessage,headers,HttpStatus.OK);
    }

    @RequestMapping(value ="/getnoticereview", method = RequestMethod.GET)
    public ResponseEntity<SendMessage<List<NoticeReviewVO>>> GetNoticeReview(HttpServletRequest request, Long notice_seq){
        Map<String, Object> auth = jwtService.requestAuthorization(request);
        SendMessage<List<NoticeReviewVO>> sendMessage = null;
        HttpHeaders headers = new HttpHeaders();
        List<NoticeReviewVO> noticeReviewVOList = new ArrayList<>();
        headers.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));
        if(notice_seq <= 0){
            return new ResponseEntity<>(null,headers,HttpStatus.BAD_REQUEST);
        }
        if(auth == null){
            sendMessage = new SendMessage<>(null,StatusEnum.UNAUTHORIZED,"Token Expired");
            return new ResponseEntity<>(sendMessage,headers, HttpStatus.UNAUTHORIZED);
        }
        noticeReviewVOList = noticeReviewService.getNoticeReviewList(notice_seq);
        if(noticeReviewVOList == null){
            sendMessage = new SendMessage<>(null,StatusEnum.INTERNAL_SERVER_ERROOR,"SERVER ERROR ");
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(sendMessage,headers,HttpStatus.OK);


    }

    @RequestMapping(value = "/changenoticereview", method = RequestMethod.POST)
    public ResponseEntity<SendMessage<NoticeReviewVO>> ChangeNoticeReview(HttpServletRequest request , ChangeNoticeReviewVO changeNoticeReviewVO){
        Map<String,Object> auth  =jwtService.requestAuthorization(request);
        SendMessage<NoticeReviewVO> sendMessage = null;
        HttpHeaders headers = new HttpHeaders();
        NoticeReviewVO noticeReviewVO = null;
        headers.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));

        if(auth == null){
            sendMessage = new SendMessage<>(null,StatusEnum.UNAUTHORIZED, "TokenExpired");
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.UNAUTHORIZED);
        }
        if(changeNoticeReviewVO.getNotice_seq() < 0 || changeNoticeReviewVO.getReview_seq() < 0 || changeNoticeReviewVO.getMember_seq() <0){
            sendMessage = new SendMessage<>(null,StatusEnum.BAD_REQUEST,"BAD REQUEST");
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.BAD_REQUEST);
        }
        noticeReviewVO = noticeReviewService.getNoticeReview(changeNoticeReviewVO.getReview_seq());
        if(noticeReviewVO == null){
            sendMessage = new SendMessage<>(null,StatusEnum.BAD_REQUEST,"BAD REQEUST");
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.BAD_REQUEST);
        }
        noticeReviewVO.setContent(changeNoticeReviewVO.getContent());
        int update = noticeReviewService.UpdateNoticeReview(noticeReviewVO);
        if(update == -1 ){
            sendMessage = new SendMessage<>(null,StatusEnum.INTERNAL_SERVER_ERROOR , "INTERVAL SERVER ERROR");
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        sendMessage = new SendMessage<>(noticeReviewVO,StatusEnum.OK,"OK");
        return new ResponseEntity<>(sendMessage,headers,HttpStatus.OK);
    }

    @RequestMapping(value ="deletenoticereview", method=RequestMethod.PATCH)
    public ResponseEntity<SendMessage<Integer>> DeleteNoticeReview(HttpServletRequest request,Long notice_Seq){
        Map<String,Object> auth  =jwtService.requestAuthorization(request);
        SendMessage<Integer> sendMessage = null;
        HttpHeaders headers = new HttpHeaders();
        NoticeReviewVO noticeReviewVO = null;
        headers.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));
        return new ResponseEntity<>(sendMessage,headers,HttpStatus.OK);
    }


}
