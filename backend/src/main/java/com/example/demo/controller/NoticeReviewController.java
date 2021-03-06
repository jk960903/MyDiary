package com.example.demo.controller;

import com.example.demo.JWT.JwtService;
import com.example.demo.dao.NoticeReviewService;
import com.example.demo.dao.NoticeService;
import com.example.demo.vo.Enum.StatusEnum;
import com.example.demo.SendMessage.SendMessage;
import com.example.demo.dto.Notice.UpdateNoticeReviewRequest;
import com.example.demo.dto.Notice.AddNoticeReviewRequest;
import com.example.demo.dto.Notice.DeleteNoticeReviewRequest;
import com.example.demo.vo.notice.ReadNoticeReviewVO;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.Map;

@RestController
@RequestMapping("/api/noticereview")
@NoArgsConstructor
public class NoticeReviewController {
    private  NoticeService noticeService;

    private  NoticeReviewService noticeReviewService;

    private  JwtService jwtService;

    @Autowired
    public NoticeReviewController(NoticeService noticeService, NoticeReviewService noticeReviewService, JwtService jwtService) {
        this.noticeService = noticeService;
        this.noticeReviewService = noticeReviewService;
        this.jwtService = jwtService;
    }

    @PostMapping(value = "/addnotice-review")
    public ResponseEntity<SendMessage<ReadNoticeReviewVO>> AddNoticeReview(HttpServletRequest request, AddNoticeReviewRequest model){
        Map<String,Object> auth;
        SendMessage<ReadNoticeReviewVO> sendMessage=null;
        HttpHeaders headers = new HttpHeaders();
        ReadNoticeReviewVO noticeReviewVO;
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        int result=0;
        try{
            auth= jwtService.requestAuthorization(request);
            model.CheckLoginValidate(Long.parseLong((String)auth.get("idx")));
            model.CheckValidate();
            noticeReviewVO = ReadNoticeReviewVO.builder().noticeidx(model.getNotice_idx()).content(model.getContent())
                    .memberidx(model.getMember_idx()).isDeleted(1).build();
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


    @PatchMapping(value = "/updatenotice-review")
    public ResponseEntity<SendMessage<ReadNoticeReviewVO>> UpdateNoticeReview(HttpServletRequest request , UpdateNoticeReviewRequest changeNoticeReviewVO){
        Map<String,Object> auth;
        SendMessage<ReadNoticeReviewVO> sendMessage=null;
        HttpHeaders headers = new HttpHeaders();
        ReadNoticeReviewVO noticeReviewVO;
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        try {
            auth = jwtService.requestAuthorization(request);
            changeNoticeReviewVO.CheckValidate();
            noticeReviewVO= noticeReviewService.UpdateNoticeReview(changeNoticeReviewVO);
        } catch (IllegalAccessException e) {
            sendMessage=new SendMessage<>(null,StatusEnum.UNAUTHORIZED,e.getMessage());
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.UNAUTHORIZED);
        } catch(NullPointerException e){
            sendMessage=new SendMessage<>(null,StatusEnum.BAD_REQUEST,e.getMessage());
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.BAD_REQUEST);
        } catch(IndexOutOfBoundsException e){
            sendMessage=new SendMessage<>(null,StatusEnum.NOT_FOUND,e.getMessage());
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            sendMessage=new SendMessage<>(null,StatusEnum.INTERNAL_SERVER_ERROOR,e.getMessage());
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        sendMessage = new SendMessage<>(noticeReviewVO,StatusEnum.OK,"OK");
        return new ResponseEntity<>(sendMessage,headers,HttpStatus.OK);
    }

    @PatchMapping(value ="deletenotice-review")
    public ResponseEntity<SendMessage<Integer>> DeleteNoticeReview(HttpServletRequest request, DeleteNoticeReviewRequest model){
        Map<String,Object> auth;
        SendMessage<Integer> sendMessage=null;
        HttpHeaders headers = new HttpHeaders();
        ReadNoticeReviewVO noticeReviewVO;
        headers.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));
        int result=0;
        try{
            auth=jwtService.requestAuthorization(request);
            model.CheckValidate();
            result= noticeReviewService.DeleteNoticeReview(model);
        } catch (IllegalAccessException e) {
            sendMessage=new SendMessage<>(null,StatusEnum.UNAUTHORIZED,e.getMessage());
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.UNAUTHORIZED);
        } catch(NullPointerException e){
            sendMessage=new SendMessage<>(null,StatusEnum.BAD_REQUEST,e.getMessage());
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.BAD_REQUEST);
        }catch(IndexOutOfBoundsException e){
            sendMessage = new SendMessage<>(null,StatusEnum.NOT_FOUND,e.getMessage());
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            sendMessage=new SendMessage<>(null,StatusEnum.INTERNAL_SERVER_ERROOR,e.getMessage());
            return new ResponseEntity<>(sendMessage,headers,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        sendMessage=new SendMessage<>(1,StatusEnum.OK,"API OK");
        return new ResponseEntity<>(sendMessage,headers,HttpStatus.OK);
    }


}
