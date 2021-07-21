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
	
	public int DeleteNoticeDetail(NoticeDetailVO noticeDetail) {
		return noticeDetailRepository.DeleteNoticeDetail(noticeDetail);
	}
	
	public int UpdateNoticeDetail(NoticeDetailVO noticeDetail) {
		return noticeDetailRepository.UpdateNoticeDetail(noticeDetail);
	}
	
	public int InsertNoticeDetail(NoticeDetailVO noteiceDetail) {
		return noticeDetailRepository.InsertNoticeDetail(noteiceDetail);
	}
}
