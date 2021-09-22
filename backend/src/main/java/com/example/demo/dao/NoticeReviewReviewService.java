package com.example.demo.dao;

import com.example.demo.dto.Notice.UpdateNoticeReviewReviewRequest;
import com.example.demo.vo.notice.NoticeReviewReviewVO;
import com.example.demo.vo.notice.ReadNoticeReviewReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public NoticeReviewReviewVO AddNoticeReviewReview(NoticeReviewReviewVO review) throws Exception{
        NoticeReviewReviewVO noticeReviewReviewVO;
        try{
            noticeReviewReviewVO=noticeReviewReviewRepository.save(review);
        }catch(Exception e){
            throw new Exception("INTERVAL SERVERERROR");
        }
        return noticeReviewReviewVO;
    }

    public NoticeReviewReviewVO UpdateNoticeReviewReview(UpdateNoticeReviewReviewRequest updateRequest) throws Exception{
        NoticeReviewReviewVO noticeReviewReviewVO;
        try{
            noticeReviewReviewVO=noticeReviewReviewRepository.findByIdx(updateRequest.getIdx()).get(0);
            noticeReviewReviewVO.setContent(updateRequest.getContent());
            noticeReviewReviewVO=noticeReviewReviewRepository.save(noticeReviewReviewVO);
        }catch (Exception e){
            throw new Exception("INTERVAL SERVER ERROR");
        }
        return noticeReviewReviewVO;
    }

    @Transactional
    @Modifying
    public NoticeReviewReviewVO DeleteNoticeReviewReview(Long idx) throws IndexOutOfBoundsException,Exception{
        NoticeReviewReviewVO noticeReviewReviewVO;
        try{
            noticeReviewReviewVO=noticeReviewReviewRepository.findByIdx(idx).get(0);
            noticeReviewReviewVO.setIsdeleted(9);
            noticeReviewReviewVO=noticeReviewReviewRepository.save(noticeReviewReviewVO);
        }catch(IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("찾으시는 항목이 없습니다.");
        }
        catch(Exception e){
            throw new Exception("INTERNAL SERVER ERROR");
        }
        return noticeReviewReviewVO;
    }
}
