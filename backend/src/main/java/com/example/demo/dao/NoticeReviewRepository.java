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


    public List<NoticeReviewVO> findByIdxAndIsDeleted(Long idx,Integer IsDeleted);

    public List<NoticeReviewVO> findNoticeReviewVOByNoticeidxAndIsDeleted(Long NoticeIdx,Integer IsDeleted);
}
