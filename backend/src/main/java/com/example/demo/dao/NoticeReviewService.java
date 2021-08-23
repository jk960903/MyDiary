package com.example.demo.dao;

import com.example.demo.vo.notice.NoticeReviewVO;
import jdk.jshell.spi.ExecutionControlProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeReviewService {
    @Autowired
    NoticeReviewRepository noticeReviewRepository;

    public int AddNoticeReview(NoticeReviewVO noticeReviewVO) throws Exception{
        try{
            noticeReviewRepository.AddNoticeReview(noticeReviewVO);
        }catch(Exception e){
            // 삽입 실패
            System.out.println(e.getMessage());
            throw new Exception("INTERVAL SERVER ERROR");
        }
        return 1;
    }
    public List<NoticeReviewVO> getNoticeReviewList(Long notice_seq) throws Exception{
        List<NoticeReviewVO> list = new ArrayList<>();
        try{
            list = noticeReviewRepository.getNoticeReviewList(notice_seq);
        }catch (Exception e){
            throw new Exception("THERE IS NO DATA");
        }
        return list;
    }

    //수정하기 전 댓글이 있는지 확인
    public NoticeReviewVO getNoticeReview(Long Seq) throws Exception{
        NoticeReviewVO noticeReviewVO =null;
        try{
            noticeReviewVO = noticeReviewRepository.getNoticeReview(Seq).get(0);
        }catch(IndexOutOfBoundsException e){
            throw new Exception("NO EXISTING REVIEW");
        }
        return noticeReviewVO;
    }

    public int UpdateNoticeReview(NoticeReviewVO noticeReviewVO) throws Exception{
        try{
            noticeReviewRepository.UpdateNoticeReview(noticeReviewVO);
        }catch (Exception e){
            throw new Exception("INTERVAL SERVER ERROR");
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
