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

    public void DeleteNoticeDetail(NoticeDetailVO noticeDetail) throws Exception{
        try{
            noticeDetailRepository.DeleteNoticeDetail(noticeDetail);
        }catch (Exception e){
            throw new Exception("Interver SERVER ERROR");
        }

    }

    public void UpdateNoticeDetail(NoticeDetailVO noticeDetail) {
        noticeDetailRepository.UpdateNoticeDetail(noticeDetail);
    }

    public void AddNoticeDetail(NoticeDetailVO noteiceDetail) throws Exception{
        try{
            noticeDetailRepository.AddNoticeDetail(noteiceDetail);
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("INTERVAL SERVER ERROR");
        }

    }
}
