package com.example.demo.dao;

import com.example.demo.vo.notice.NoticeReviewVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeReviewRepository extends JpaRepository<NoticeReviewVO,Long> {

    @Query
    public List<NoticeReviewVO> getNoticeReviewVOByNotice_idx();

}
