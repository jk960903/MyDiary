package com.example.demo.controller;

import com.example.demo.JWT.JwtService;
import com.example.demo.dao.NoticeReviewService;
import com.example.demo.dao.NoticeService;
import com.example.demo.vo.Enum.StatusEnum;
import com.example.demo.SendMessage.SendMessage;
import com.example.demo.dto.ChangeNoticeReviewRequest;
import com.example.demo.dto.AddNoticeReviewRequest;
import com.example.demo.dto.DeleteNoticeReviewRequest;
import com.example.demo.vo.notice.NoticeReviewVO;
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
@RequestMapping("/api/noticereview")
public class NoticeReviewController {
    private final NoticeService noticeService;

    private final NoticeReviewService noticeReviewService;

    private final JwtService jwtService;

    public NoticeReviewController(NoticeService noticeService, NoticeReviewService noticeReviewService, JwtService jwtService) {
        this.noticeService = noticeService;
        this.noticeReviewService = noticeReviewService;
        this.jwtService = jwtService;
    }

    @RequestMapping(value = "/addnotice-review", method = RequestMethod.POST)
    public ResponseEntity<SendMessage<NoticeReviewVO>> AddNoticeReview(HttpServletRequest request, AddNoticeReviewRequest model){
        Map<String,Object> auth;
        SendMessage<NoticeReviewVO> sendMessage=null;
        HttpHeaders headers = new HttpHeaders();
        NoticeReviewVO noticeReviewVO;
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        int result=0;
        try{
            auth= jwtService.requestAuthorization(request);
            model.CheckLoginValidate(Long.parseLong((String)auth.get("idx")));
            model.CheckValidate();
            noticeReviewVO = NoticeReviewVO.builder().noticeidx(model.getNotice_idx()).content(model.getContent())
                    .memberidx(model.getMember_idx()).isDeleted(Byte.parseByte("1")).build();
            result= noticeReviewService.AddNoticeReview(noticeReviewVO);
        } catch (IllegalAccessException e) {
            sendMessage=new SendMessage<>(null,StatusEnum.UNAUTHORIZED,e.getMessage());
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.UNAUTHORIZED);
        } catch(NullPointerException e){
            sendMessage=new SendMessage<>(null,StatusEnum.BAD_REQUEST,e.getMessage());
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            sendMessage=new SendMessage<>(null,StatusEnum.INTERNAL_SERVER_ERROOR,e.getMessage());
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        sendMessage = new SendMessage<>(noticeReviewVO,StatusEnum.OK,"OK");
        return new ResponseEntity<>(sendMessage,headers,HttpStatus.OK);
    }
    //단순 보이는 것이기 때문에 로그인 필요없음
    @RequestMapping(value ="/getnotice-review", method = RequestMethod.GET)
    public ResponseEntity<SendMessage<List<NoticeReviewVO>>> GetNoticeReview(HttpServletRequest request, Long notice_seq){
        SendMessage<List<NoticeReviewVO>> sendMessage=null;
        HttpHeaders headers = new HttpHeaders();
        List<NoticeReviewVO> noticeReviewVOList = new ArrayList<>();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        try{
            if(notice_seq <= 0){
                return new ResponseEntity<>(null,headers,HttpStatus.BAD_REQUEST);
            }
            noticeReviewVOList = noticeReviewService.getNoticeReviewList(notice_seq);

        } catch (Exception e) {
            sendMessage = new SendMessage<>(null,StatusEnum.INTERNAL_SERVER_ERROOR,e.getMessage());
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(noticeReviewVOList == null){
            sendMessage = new SendMessage<>(null,StatusEnum.INTERNAL_SERVER_ERROOR,"SERVER ERROR ");
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(sendMessage,headers,HttpStatus.OK);


    }

    @RequestMapping(value = "/updatenotice-review", method = RequestMethod.PATCH)
    public ResponseEntity<SendMessage<NoticeReviewVO>> UpdateNoticeReview(HttpServletRequest request , ChangeNoticeReviewRequest changeNoticeReviewVO){
        Map<String,Object> auth;
        SendMessage<NoticeReviewVO> sendMessage=null;
        HttpHeaders headers = new HttpHeaders();
        NoticeReviewVO noticeReviewVO;
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        int result=0;
        try {
            auth = jwtService.requestAuthorization(request);
            changeNoticeReviewVO.CheckLoginValidate(Long.parseLong((String)auth.get("idx")));
            changeNoticeReviewVO.CheckValidate();
            noticeReviewVO = noticeReviewService.getNoticeReview(changeNoticeReviewVO.getReview_idx());
            noticeReviewVO.setContent(changeNoticeReviewVO.getContent());
            result= noticeReviewService.UpdateNoticeReview(noticeReviewVO);
        } catch (IllegalAccessException e) {
            sendMessage=new SendMessage<>(null,StatusEnum.UNAUTHORIZED,e.getMessage());
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.UNAUTHORIZED);
        } catch(NullPointerException e){
            sendMessage=new SendMessage<>(null,StatusEnum.BAD_REQUEST,e.getMessage());
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            sendMessage=new SendMessage<>(null,StatusEnum.INTERNAL_SERVER_ERROOR,e.getMessage());
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        sendMessage = new SendMessage<>(noticeReviewVO,StatusEnum.OK,"OK");
        return new ResponseEntity<>(sendMessage,headers,HttpStatus.OK);
    }

    @RequestMapping(value ="deletenotice-review", method=RequestMethod.PATCH)
    public ResponseEntity<SendMessage<Integer>> DeleteNoticeReview(HttpServletRequest request, DeleteNoticeReviewRequest model){
        Map<String,Object> auth;
        SendMessage<Integer> sendMessage=null;
        HttpHeaders headers = new HttpHeaders();
        NoticeReviewVO noticeReviewVO;
        headers.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));
        int result=0;
        try{
            auth=jwtService.requestAuthorization(request);
            model.CheckLoginValidate(Long.parseLong((String)auth.get("idx")));
            model.CheckValidate();
            noticeReviewVO = noticeReviewService.getNoticeReview(model.getReview_idx());
            result= noticeReviewService.DeleteNoticeReview(noticeReviewVO.getIdx());
        } catch (IllegalAccessException e) {
            sendMessage=new SendMessage<>(null,StatusEnum.UNAUTHORIZED,e.getMessage());
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.UNAUTHORIZED);
        } catch(NullPointerException e){
            sendMessage=new SendMessage<>(null,StatusEnum.BAD_REQUEST,e.getMessage());
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            sendMessage=new SendMessage<>(null,StatusEnum.INTERNAL_SERVER_ERROOR,e.getMessage());
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        sendMessage=new SendMessage<>(1,StatusEnum.OK,"API OK");
        return new ResponseEntity<>(sendMessage,headers,HttpStatus.OK);
    }


}
