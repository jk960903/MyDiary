package com.example.demo.Repository.Notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.VO.Notice.NoticeVO;

public class NoticeService {
	@Autowired 
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
