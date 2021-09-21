package com.example.demo.dao;

import com.example.demo.vo.notice.NoticeReviewReviewVO;
import com.example.demo.vo.notice.ReadNoticeReviewReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeReviewReviewService {

    private NoticeReviewReviewRepository noticeReviewReviewRepository;


    @Autowired
    public NoticeReviewReviewService(NoticeReviewReviewRepository noticeReviewReviewRepository){
        this.noticeReviewReviewRepository = noticeReviewReviewRepository;
    }
    public List<ReadNoticeReviewReviewVO> GetNoticeReviewReviewList(Long notice_reviewidx){
        List<ReadNoticeReviewReviewVO> list= new ArrayList<>();
        try{
            list=noticeReviewReviewRepository.findNoticeReviewReviewVOByReviewidxAndIsdeleted(notice_reviewidx,1);
            if(list.size() <=0){
                list = new ArrayList<>();
            }
        }catch(Exception e){
            list= null;
        }
        return list;
    }

    public NoticeReviewReviewVO GetNoticeReviewReview(Long idx) throws NullPointerException,Exception{
        NoticeReviewReviewVO result;
        try{
            result=noticeReviewReviewRepository.GetNoticeReviewReview(idx).get(0);
        }catch(NullPointerException e){
            throw new NullPointerException("BAD REQUEST");
        }catch(Exception e){
            throw new Exception("INTERVAL SERVER ERROR");
        }
        return result;
    }

    public NoticeReviewReviewVO AddNoticeReviewReview(NoticeReviewReviewVO review) throws Exception{
        try{
            noticeReviewReviewRepository.AddNoticeReviewReview(review);
        }catch(Exception e){
            throw new Exception("INTERVAL SERVERERROR");
        }
        return null;
    }

    public void UpdateNoticeReviewReview(NoticeReviewReviewVO noticeReviewReviewVO) throws Exception{
        try{
            noticeReviewReviewRepository.UpdateNoticeReviewReview(noticeReviewReviewVO);
        }catch (Exception e){
            throw new Exception("INTERVAL SERVER ERROR");
        }
    }

    public void DeleteNoticeReviewReview(Long idx) throws Exception{
        try{
            noticeReviewReviewRepository.DeleteNoticeReviewReview(idx);
        }catch(Exception e){
            throw new Exception("INTERNAL SERVER ERROR");
        }
    }
}
