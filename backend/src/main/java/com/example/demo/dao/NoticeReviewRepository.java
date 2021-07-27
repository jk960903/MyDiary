package com.example.demo.dao;

import com.example.demo.vo.notice.NoticeReviewVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeReviewRepository extends JpaRepository<NoticeReviewVO,Long> {
}
