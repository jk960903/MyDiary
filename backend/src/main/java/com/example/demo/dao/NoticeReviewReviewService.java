package com.example.demo.dao;

import com.example.demo.vo.notice.NoticeReviewReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeReviewReviewService {
    @Autowired(required = true)
    NoticeReviewReviewRepository noticeReviewReviewRepository;

    public List<NoticeReviewReviewVO> GetNoticeReviewReview(Long notice_reviewidx){
        List<NoticeReviewReviewVO> list= new ArrayList<>();
        try{
            list=noticeReviewReviewRepository.GetNoticeReviewReview(notice_reviewidx);
            if(list.size() <=0){
                list = new ArrayList<>();
            }
        }catch(Exception e){
            list= null;
        }
        return list;
    }

    public NoticeReviewReviewVO AddNoticeReviewReview(NoticeReviewReviewVO review){
        NoticeReviewReviewVO result= noticeReviewReviewRepository.AddNoticeReviewReview(review);

        return result;
    }
}
