package com.example.demo.dao;

import com.example.demo.vo.notice.NoticeReviewReviewVO;
import com.example.demo.vo.notice.ReadNoticeReviewReviewVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface NoticeReviewReviewRepository extends JpaRepository<NoticeReviewReviewVO,Long> {



    public List<ReadNoticeReviewReviewVO> findNoticeReviewReviewVOByReviewidxAndIsdeleted(Long ReviewIdx, Integer IsDeleted);

    public List<NoticeReviewReviewVO> findByIdx(Long idx);
}

