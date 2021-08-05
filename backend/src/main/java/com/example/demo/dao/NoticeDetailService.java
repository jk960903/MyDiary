package com.example.demo.dao;

import com.example.demo.vo.notice.NoticeDetailVO;
import com.example.demo.vo.notice.NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeDetailService {
    @Autowired(required=true)
    private NoticeDetailRepository noticeDetailRepository;


    public List<NoticeDetailVO> GetNoticeDetail(Long idx) {
        return noticeDetailRepository.GetNoticeDetail(idx);
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
