package com.example.demo.controller;

import com.example.demo.JWT.JwtService;
import com.example.demo.dao.NoticeReviewRepository;
import com.example.demo.dao.NoticeReviewService;
import com.example.demo.dao.NoticeService;
import com.example.demo.vo.Category.CategoryVO;
import com.example.demo.vo.Enum.StatusEnum;
import com.example.demo.vo.SendMessage;
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
import java.util.Map;

@RestController
@RequestMapping("/NoticeReview")
public class NoticeReviewController {
    @Autowired(required = true)
    private NoticeService noticeService;

    @Autowired(required = true)
    private NoticeReviewService noticeReviewService;
    @Autowired(required = true)
    private JwtService jwtService;

    @RequestMapping(value = "/AddNoticeReview", method = RequestMethod.GET)
    public ResponseEntity<SendMessage<NoticeReviewVO>> AddNoticeReview(HttpServletRequest request, NoticeReviewReqeust model){

        Map<String, Object> auth= jwtService.requestAuthorization(request);
        SendMessage<NoticeReviewVO> sendMessage=null;
        HttpHeaders headers = new HttpHeaders();
        CategoryVO deleteCategory;
        NoticeReviewVO noticeReviewVO=null;
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        if(model.getNotice_idx() < 0 || model.getMember_idx()  < 0 ) return new ResponseEntity<>(null,headers, HttpStatus.BAD_REQUEST);
        if(auth == null){
            sendMessage = new SendMessage<NoticeReviewVO>(noticeReviewVO, StatusEnum.UNAUTHORIZED,"Token Expired");
            return new ResponseEntity<SendMessage<NoticeReviewVO>>(sendMessage , headers, HttpStatus.UNAUTHORIZED);
        }
        noticeReviewVO = NoticeReviewVO.builder().noticeidx(model.getNotice_idx()).content(model.getContent())
                .memberidx(model.getMember_idx()).isDeleted(Byte.parseByte("1")).build();
        int queryresult = noticeReviewService.AddNoticeReview(noticeReviewVO);
        if(queryresult <0) {

        }
        

    }
}
