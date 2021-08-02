package com.example.demo.dao;

import com.example.demo.vo.notice.NoticeReviewReviewVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeReviewReviewRepository extends JpaRepository<NoticeReviewReviewVO,Long> {
}
