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

    @Query(value = "select notice.viewcount from notice where notice.idx=:idx",nativeQuery = true)
    public List<NoticeVO> GetNoticeViewCount(Long idx);

    @Query(value= "update notice set notice.viewcount = :#{#updateNoticeCountRequest.viewCount} where notice.idx = :#{#updateNoticeCountRequest.idx}",nativeQuery = true)
    @Modifying
    @Transactional
    public void UpdateNotoiceView(UpdateNoticeCountRequest updateNoticeCountRequest);

    @Query(value="update notice set notice.isdeleted =1 where notice.idx=:notice_idx",nativeQuery = true)
    @Modifying
    @Transactional
    public void DeleteNotice(Long notice_idx);
}
