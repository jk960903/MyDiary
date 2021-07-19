package com.example.demo.Repository.Notice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.VO.Notice.NoticeDetailVO;

@Repository
public interface NoticeDetailRepository extends JpaRepository<NoticeDetailVO,Long>{
	
}
