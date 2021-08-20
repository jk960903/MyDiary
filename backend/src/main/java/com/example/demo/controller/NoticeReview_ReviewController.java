package com.example.demo.controller;


import com.example.demo.JWT.JwtService;
import com.example.demo.dao.NoticeReviewReviewService;
import com.example.demo.vo.notice.NoticeReviewReviewVO;
import com.example.demo.vo.notice.NoticeReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/notice-review-review")
public class NoticeReview_ReviewController {

    @Autowired(required = true)
    NoticeReviewReviewService noticeReviewReviewService;

    @Autowired(required = true)
    JwtService jwtService;
    @RequestMapping(value ="/getnotice_reivew_review_list")
    public ResponseEntity<List<NoticeReviewReviewVO>> getNoticeReviewReviewList(Long notice_review_idx){
        return null;
    }

    @RequestMapping(value="/addnotice_review_review")
    public ResponseEntity<NoticeReviewReviewVO> AddNoticeReviewReview(NoticeReviewReviewVO noticeReviewReviewVO){
        return null;
    }

    @RequestMapping(value="/updatenotice_review_reivew")
    public ResponseEntity<NoticeReviewReviewVO> UpdateNoticeReviewReviewVO(NoticeReviewReviewVO noticeReviewReviewVO){
        return null;
    }

    @RequestMapping(value="/deletenotice_review_review")
    public ResponseEntity<NoticeReviewReviewVO> DeleteNoticeReviewReviewVO(NoticeReviewReviewVO noticeReviewReviewVO){
        return null;
    }
}
