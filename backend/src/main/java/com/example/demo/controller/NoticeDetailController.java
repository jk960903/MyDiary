package com.example.demo.controller;

import com.example.demo.dao.NoticeDetailRepository;
import com.example.demo.dao.NoticeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/NoticeDetail")
public class NoticeDetailController {
    @Autowired(required = true)
    NoticeDetailService noticeDetailService;



}
