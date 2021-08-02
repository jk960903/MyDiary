package com.example.demo.dao;

import com.example.demo.vo.notice.NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    @Autowired(required=true)
    private NoticeRepository noticeRepository;

    public List<NoticeVO> GetNoticeList(){
        return noticeRepository.GetNoticeList();
    }

    public List<NoticeVO> GetSearchNoticeList(String search){
        return noticeRepository.GetSearchNoticeList(search);
    }

    public void AddNotice(NoticeVO notice) {
        noticeRepository.AddNotice(notice);
    }
}
