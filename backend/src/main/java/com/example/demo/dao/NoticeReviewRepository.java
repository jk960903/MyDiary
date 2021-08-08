package com.example.demo.dao;

import com.example.demo.vo.notice.NoticeReviewVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeReviewRepository extends JpaRepository<NoticeReviewVO,Long> {


    @Query(value ="insert into notice_review(noticeidx,content,memberidx,regdate,isdeleted) value(:#{#reviewVO.noticeidx},:#{#reviewVO.content},:#{#reviewVO.memberidx},now(),1)")
    public void AddNoticeReview(NoticeReviewVO reviewVO);

    @Query(value = "select * from notice_review where notice_review.notice_seq = :Notice_Seq")
    public List<NoticeReviewVO> getNoticeReviewList(Long Notice_Seq);
}
