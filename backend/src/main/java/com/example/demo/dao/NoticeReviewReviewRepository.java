package com.example.demo.dao;

import com.example.demo.vo.notice.NoticeReviewReviewVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface NoticeReviewReviewRepository extends JpaRepository<NoticeReviewReviewVO,Long> {

    /*@Query
    public List<NoticeReviewReviewVO> GetNoticeReviewReview();

    @Query
    @Transactional
    @Modifying
    public NoticeReviewReviewVO AddNoticeReviewReview();

    @Query
    @Transactional
    @Modifying
    public void ChangeNoticeReviewReview();

    @Query
    @Transactional
    @Modifying
    public void DeleteNoticeReviewReview();*/

}

