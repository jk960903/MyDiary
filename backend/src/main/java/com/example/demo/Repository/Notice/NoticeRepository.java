package com.example.demo.Repository.Notice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.VO.Notice.NoticeVO;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeVO,Long>{
	
	@Query(value = "select * from notice", nativeQuery = true)
	public List<NoticeVO> GetNoticeList();
	
	@Query(value = "select * from notice where notice.title = :search" ,nativeQuery = true)
	public List<NoticeVO> GetSearchNoticeList(String search);
	
	@Modifying
	@Query(value = "insert into notice(title,writer,regdate,viewcount)"
			+"value(:#{#notice.title},:#{#notice.writer},now(),0)", nativeQuery = true)
	@Transactional
	public void AddNotice(NoticeVO notice);
	
	
	
}
