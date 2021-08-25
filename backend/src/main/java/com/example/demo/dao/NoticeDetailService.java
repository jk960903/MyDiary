package com.example.demo.dao;

import com.example.demo.vo.notice.NoticeDetailVO;
import com.example.demo.vo.notice.NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeDetailService {

    private NoticeDetailRepository noticeDetailRepository;

    @Autowired
    public NoticeDetailService (NoticeDetailRepository noticeDetailRepository){
        this.noticeDetailRepository=noticeDetailRepository;
    }



    public NoticeDetailVO GetNoticeDetail(Long idx) throws NullPointerException,Exception{
        NoticeDetailVO noticeDetailVO =null;
        try{
            noticeDetailRepository.GetNoticeDetail(idx).get(0);
        }catch(IndexOutOfBoundsException e){
            throw new NullPointerException("NO DATA");
        }catch(Exception e){
            throw new Exception("INTERVAL SERVER ERROR");
        }
        return noticeDetailVO;
    }

    public void DeleteNoticeDetail(NoticeDetailVO noticeDetail) {
        noticeDetailRepository.DeleteNoticeDetail(noticeDetail);

    }

    public void UpdateNoticeDetail(NoticeDetailVO noticeDetail) {
        noticeDetailRepository.UpdateNoticeDetail(noticeDetail);
    }

    public void InsertNoticeDetail(NoticeDetailVO noteiceDetail) {
        noticeDetailRepository.InsertNoticeDetail(noteiceDetail);
    }
}
