package com.example.demo.dao;

import com.example.demo.dto.Notice.UpdateNoticeDetailRequest;
import com.example.demo.vo.notice.NoticeDetailVO;
import com.example.demo.vo.notice.NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeDetailService {

    private NoticeDetailRepository noticeDetailRepository;

    @Autowired
    public NoticeDetailService (NoticeDetailRepository noticeDetailRepository){
        this.noticeDetailRepository=noticeDetailRepository;
    }



    public NoticeDetailVO GetNoticeDetail(Long idx) throws IndexOutOfBoundsException,Exception{
        NoticeDetailVO noticeDetailVO =null;
        try{
            noticeDetailVO = noticeDetailRepository.findByIdx(idx).get(0);
        }catch(IndexOutOfBoundsException e){
            throw new NullPointerException("NO DATA");
        }catch(Exception e){
            throw new Exception("INTERVAL SERVER ERROR");
        }
        return noticeDetailVO;
    }

    public void DeleteNoticeDetail(NoticeDetailVO noticeDetail) throws IndexOutOfBoundsException, Exception{
        NoticeDetailVO noticeDetailVO = null;
        try{
            noticeDetailVO = noticeDetailRepository.findByIdx(noticeDetail.getIdx()).get(0);
            noticeDetail.setIsdeleted(9);
            noticeDetailRepository.save(noticeDetail);
        }catch(IndexOutOfBoundsException e){
            throw new Exception("찾으시는 데이터가 없습니다.");
        }
        catch (Exception e){
            throw new Exception("Interver SERVER ERROR");
        }

    }

    public void UpdateNoticeDetail(UpdateNoticeDetailRequest noticeDetail) throws IndexOutOfBoundsException,Exception{
        NoticeDetailVO noticeDetailVO = null;
        try{
            noticeDetailVO = noticeDetailRepository.findByIdx(noticeDetail.getIdx()).get(0);
            noticeDetailVO.setContent(noticeDetail.getContent());
            noticeDetailRepository.save(noticeDetailVO);
        }catch(IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("찾으시는 데이터가 없습니다.");
        }catch(Exception e){
            throw new Exception("INTERNAL SERVER ERROR");
        }
        return;
    }

    public void AddNoticeDetail(NoticeDetailVO noticeDetail) throws Exception{
        try{
            noticeDetailRepository.save(noticeDetail);
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("INTERVAL SERVER ERROR");
        }

    }
}
