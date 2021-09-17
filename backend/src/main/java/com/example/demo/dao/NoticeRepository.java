package com.example.demo.dao;

import com.example.demo.dto.Notice.UpdateNoticeCountRequest;
import com.example.demo.vo.notice.NoticeVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeVO,Long>{

    public List<NoticeVO> findAll();

    public List<NoticeVO> findByTitleLikeAndIsDeleted(String title,Integer isDeleted);

    public List<NoticeVO> findByIdx(Long idx);


}
