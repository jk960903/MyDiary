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

    @Query(value="select * from notice_review_reivew where notice_review_review.reviewidx = :reviewidx", nativeQuery = true)
    public List<NoticeReviewReviewVO> GetNoticeReviewReview(Long reviewidx);

    @Query(value="insert into notice_reveiew_review(reviewidx,memberidx,content,regdate,isdeleted) " +
            "value(:#{#review.reviewidx},:#{#review.memberidx},:#{#review.content},now(),1)})",nativeQuery = true)
    @Transactional
    @Modifying
    public NoticeReviewReviewVO AddNoticeReviewReview(NoticeReviewReviewVO review);

    /*@Query
    @Transactional
    @Modifying
    public void ChangeNoticeReviewReview();

    @Query
    @Transactional
    @Modifying
    public void DeleteNoticeReviewReview();*/

}

