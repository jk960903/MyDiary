package com.example.demo.dao;

import com.example.demo.vo.notice.NoticeReReviewVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeReReviewRepository extends JpaRepository<NoticeReReviewVO,Long> {
}
