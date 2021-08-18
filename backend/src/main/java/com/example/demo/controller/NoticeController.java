package com.example.demo.controller;

import com.example.demo.JWT.JwtService;
import com.example.demo.dao.NoticeService;
import com.example.demo.vo.NoticeRequest;
import com.example.demo.vo.notice.NoticeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value="api/notice")
public class NoticeController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired(required=true)
    NoticeService noticeService;

    @Autowired(required = true)
    JwtService jwtService;

    /*@RequestMapping(value="/notice")//공지사항들만 보여주는 리스트
    public ModelAndView GetNoticeList() {
        return new ModelAndView("Notice");
    }*/

    @RequestMapping(value="/noticeget", method = RequestMethod.GET)
    public List<NoticeVO> NoticeGet(NoticeRequest request, HttpServletRequest servletRequest){
        List<NoticeVO> list;
        if(request.getEmail().equals("")) {
            list=noticeService.GetNoticeList();
        }else {
            list=noticeService.GetSearchNoticeList(request.getEmail());
        }

        return list;
    }

    /*@RequestMapping(value="/oticeAdd")
    //공지사항 작성 페이지로 글을 작성하는것 NoticeAction으로 처리할땐 페이지에서 만약 들어가지더라도 권한레벨? 을 처리하는게 좋을듯 싶습니다.
    public ModelAndView NoticeAddPage(){
        return new ModelAndView("NoticeAddPage");
    }*/
}
