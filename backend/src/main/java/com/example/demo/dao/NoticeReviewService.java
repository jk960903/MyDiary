package com.example.demo.dao;

import com.example.demo.vo.notice.NoticeReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<NoticeReviewVO> getNoticeReviewList(Long notice_seq){
        List<NoticeReviewVO> list = new ArrayList<>();
        try{
            list = noticeReviewRepository.getNoticeReviewList(notice_seq);
        }catch (Exception e){
            list = null;
        }
        return list;
    }

    public NoticeReviewVO getNoticeReview(Long Seq){
        NoticeReviewVO noticeReviewVO =null;
        try{
            noticeReviewVO = noticeReviewRepository.getNoticeReview(Seq).get(0);
        }catch(Exception e){
            noticeReviewVO = null;
        }
        return noticeReviewVO;
    }

    public int UpdateNoticeReview(NoticeReviewVO noticeReviewVO){
        try{
            noticeReviewRepository.UpdateNoticeReview(noticeReviewVO);
        }catch (Exception e){
            return -1;
        }
        return 1;
    }
    public int DeleteNoticeReview(Long idx){
        //delete 시도
        try{
            noticeReviewRepository.DeleteNoticeReview(idx);
        }catch(Exception e){
            //실패
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

}
