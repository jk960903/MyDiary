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
@RequestMapping(value="/Notice")
public class NoticeController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired(required=true)
    NoticeService noticeService;

    @Autowired(required = true)
    JwtService jwtService;
    @RequestMapping(value="/Notice")
    public ModelAndView GetNoticeList() {
        return new ModelAndView("Notice");
    }

    @RequestMapping(value="/NoticeGet", method = RequestMethod.GET)
    public List<NoticeVO> NoticeGet(NoticeRequest request, HttpServletRequest servletRequest){
        List<NoticeVO> list;
        if(request.getEmail().equals("")) {
            list=noticeService.GetNoticeList();
        }else {
            list=noticeService.GetSearchNoticeList(request.getEmail());
        }

        return list;
    }
}
