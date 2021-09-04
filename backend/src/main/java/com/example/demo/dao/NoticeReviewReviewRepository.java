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

    @Query(value="select * from notice_review_reivew where notice_review_review.reviewidx = :reviewidx and notice_review_review.isdeleted=1", nativeQuery = true)
    public List<NoticeReviewReviewVO> GetNoticeReviewReviewList(Long reviewidx);

    @Query(value="select * from notice_review_reivew where notice_review_review.idx=:idx",nativeQuery = true)
    public List<NoticeReviewReviewVO> GetNoticeReviewReview(Long idx);

    @Query(value="insert into notice_reveiew_review(reviewidx,memberidx,content,regdate,isdeleted) " +
            "value(:#{#review.reviewidx},:#{#review.memberidx},:#{#review.content},now(),1)})",nativeQuery = true)
    @Transactional
    @Modifying
    public void AddNoticeReviewReview(NoticeReviewReviewVO review);

    @Query(value ="update notice_review_reivew set notice_review_review.content = :#{#noticeReviewReviewVO.contnet}, " +
            "notice_review_reivew.regdate=now() where notice_review_review.idx = :#{#noticeReveiewReivewVO.idx}",nativeQuery = true)
    @Transactional
    @Modifying
    public void UpdateNoticeReviewReview(NoticeReviewReviewVO noticeReviewReviewVO);

    @Query(value="update notice_review_review set notice_review_review.isdeleted=9 where notice_review_review.idx=:idx",nativeQuery = true)
    @Transactional
    @Modifying
    public void DeleteNoticeReviewReview(Long idx);


}

