package com.example.demo.dao;

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
        return noticeRepository.GetNoticeList();
    }

    public List<NoticeVO> GetSearchNoticeList(String search){
        return noticeRepository.GetSearchNoticeList(search);
    }

    public void AddNotice(NoticeVO notice) {
        noticeRepository.AddNotice(notice);
    }

    public NoticeVO GetNoticeViewCount(Long idx)throws IndexOutOfBoundsException,Exception{
        NoticeVO noticeVO = null;
        try{
            noticeVO = noticeRepository.GetNoticeViewCount(idx).get(0);
        }catch(IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("BAD REQUEST");
        }catch(Exception e){
            throw new Exception("INTERNAL SERVER ERROR");
        }
        return noticeVO;
    }

    public void PlusNotoiceView(int viewcount, Long idx) throws Exception {
        try{
            noticeRepository.PlusNotoiceView(viewcount+1,idx);
        }catch(Exception e){
            throw new Exception ("INTERNAL SERVER ERROR");
        }
    }
}
