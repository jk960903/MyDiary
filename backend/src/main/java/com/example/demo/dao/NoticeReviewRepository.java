package com.example.demo.dao;

import com.example.demo.vo.notice.NoticeReviewVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface NoticeReviewRepository extends JpaRepository<NoticeReviewVO,Long> {

    @Modifying
    @Query(value ="insert into notice_review(noticeidx,content,memberidx,regdate,isdeleted) value(:#{#reviewVO.noticeidx},:#{#reviewVO.content},:#{#reviewVO.memberidx},now(),1)", nativeQuery=true)
    @Transactional
    public void AddNoticeReview(NoticeReviewVO reviewVO);

    @Query(value = "select * from notice_review where notice_review.notice_seq = :Notice_Seq" , nativeQuery = true)
    public List<NoticeReviewVO> getNoticeReviewList(Long Notice_Seq);

    @Query(value = "select * from notice_review where notice_review.notice_seq = seq" ,nativeQuery = true)
    public List<NoticeReviewVO> getNoticeReview(Long Seq);

    @Modifying
    @Query(value = "update notice_review set notice_review.content = :#{#reviewVO.content} where notice_review.seq = :#{#reviewVO.seq}",nativeQuery = true)
    @Transactional
    public void UpdateNoticeReview(NoticeReviewVO reviewVO);
}
