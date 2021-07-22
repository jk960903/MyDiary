package com.example.demo.Repository.Notice;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.VO.Notice.NoticeDetailVO;
import com.example.demo.VO.Notice.NoticeVO;

public class NoticeDetailService {
	@Autowired
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
