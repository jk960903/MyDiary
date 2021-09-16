package com.example.demo.dao;

import com.example.demo.dto.Notice.UpdateNoticeCountRequest;
import com.example.demo.vo.notice.NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {


    private NoticeRepository noticeRepository;

    @Autowired
    public NoticeService(NoticeRepository noticeRepository){
        this.noticeRepository=noticeRepository;
    }
    public List<NoticeVO> GetNoticeList(){
        return noticeRepository.findAll();
    }

    public List<NoticeVO> GetSearchNoticeList(String search){
        return noticeRepository.findByTitleLikeAndIsDeleted(search,1);
    }

    public void AddNotice(NoticeVO notice) throws Exception{
        try{
            noticeRepository.save(notice);
        }catch(Exception e){
            throw new Exception ("INTERNAL SERVER ERROR");
        }

    }

    public NoticeVO GetNoticeViewCount(Long idx)throws IndexOutOfBoundsException,Exception{
        NoticeVO noticeVO = null;
        try{
            noticeVO = noticeRepository.findByIdx(idx).get(0);
        }catch(IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("NO DATA");
        }catch(Exception e){
            throw new Exception("INTERNAL SERVER ERROR");
        }
        return noticeVO;
    }

    public void UpdateNotoiceView(UpdateNoticeCountRequest updateNoticeCountRequest) throws Exception {
        NoticeVO noticeVO = null;
        try{
            noticeVO = noticeRepository.findByIdx(updateNoticeCountRequest.getIdx()).get(0);
            noticeRepository.UpdateNotoiceView(updateNoticeCountRequest);
        }catch(Exception e){
            throw new Exception ("INTERNAL SERVER ERROR");
        }
    }

    public void DeleteNotice(Long notice_idx) throws Exception{
        try{
            noticeRepository.DeleteNotice(notice_idx);
        }catch(Exception e){
            throw new Exception("INTERNAL SERVER ERROR");
        }
    }
}
