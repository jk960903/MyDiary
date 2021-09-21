package com.example.demo.dao;

import com.example.demo.vo.notice.ReadNoticeReviewVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeReviewRepository extends JpaRepository<ReadNoticeReviewVO,Long> {


    public List<ReadNoticeReviewVO> findByIdxAndIsDeleted(Long idx, Integer IsDeleted);

    public List<ReadNoticeReviewVO> findNoticeReviewVOByNoticeidxAndIsDeleted(Long NoticeIdx, Integer IsDeleted);
}
