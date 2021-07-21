package com.example.demo.Repository.Notice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.VO.Notice.NoticeDetailVO;
import com.example.demo.VO.Notice.NoticeVO;

@Repository
public interface NoticeDetailRepository extends JpaRepository<NoticeDetailVO,Long>{
	
	public NoticeDetailVO GetNoticeDetail(NoticeVO notice);
	
	public int DeleteNoticeDetail(NoticeDetailVO noticeDetail);
	
	public int UpdateNoticeDetail(NoticeDetailVO noticeDetail);
	
	public int InsertNoticeDetail(NoticeDetailVO noteiceDetail);
	
	
}
