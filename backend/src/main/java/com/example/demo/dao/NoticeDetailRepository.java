package com.example.demo.dao;

import com.example.demo.vo.notice.NoticeDetailVO;
import com.example.demo.vo.notice.NoticeVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface NoticeDetailRepository extends JpaRepository<NoticeDetailVO,Long>{


    public List<NoticeDetailVO> findByIdx(Long idx);

    //public List<NoticeDetailVO> findByNoticeIdxAndIsDeleted(Long NoticeIdx,Integer IsDeleted);

    public List<NoticeDetailVO> findNoticeDetailVOByNoticeidxAndIsdeleted(Long NoticeIdx,Integer IsDeleted);




}
