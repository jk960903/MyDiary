package com.example.demo.dao;

import com.example.demo.vo.notice.NoticeReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeReviewService {
    @Autowired
    NoticeReviewRepository noticeReviewRepository;

    public int AddNoticeReview(NoticeReviewVO noticeReviewVO){
        try{
            noticeReviewRepository.AddNoticeReview(noticeReviewVO);
        }catch(Exception e){
            // 삽입 실패
            System.out.println(e.getMessage());
            return -1;
        }
        return 1;
    }
}
