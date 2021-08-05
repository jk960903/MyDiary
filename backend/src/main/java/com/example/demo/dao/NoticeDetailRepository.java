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

    @Query(value="select * from notice_detail where notice_detail.noticeidx=:idx", nativeQuery=true)
    public List<NoticeDetailVO> GetNoticeDetail(Long idx);

    @Modifying
    @Query(value ="delete from notice_detail where notice_detail.idx=:#{#notice.idx", nativeQuery=true)
    public void DeleteNoticeDetail(NoticeDetailVO noticeDetail);

    @Modifying
    @Query(value ="update notice_detail set notice_detail.content = :#{#noticeDetail.content}, notice_detail.imageurl= :#{#noticeDetail.imageurl}", nativeQuery=true)
    public void UpdateNoticeDetail(NoticeDetailVO noticeDetail);

    @Modifying
    @Query(value = "insert into notice_detail(content,noticeidx,imageurl)"
            + " value(:#{#notice_detail.content}, :#{notice_detail.noticeidx , :#{#notice_detail.imageurl});",nativeQuery = true)
    @Transactional
    public void InsertNoticeDetail(NoticeDetailVO notice_detail);


}
