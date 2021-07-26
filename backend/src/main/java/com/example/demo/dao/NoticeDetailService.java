package com.example.demo.dao;

import com.example.demo.VO.Notice.NoticeDetailVO;
import com.example.demo.VO.Notice.NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeDetailService {
    @Autowired(required=true)
    private NoticeDetailRepository noticeDetailRepository;


    public NoticeDetailVO GetNoticeDetailVO(NoticeVO notice) {
        return noticeDetailRepository.GetNoticeDetail(notice);
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
