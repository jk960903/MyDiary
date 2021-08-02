package com.example.demo.controller;

import com.example.demo.JWT.JwtService;
import com.example.demo.dao.NoticeReviewRepository;
import com.example.demo.dao.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/NoticeReview")
public class NoticeReviewController {
    @Autowired(required = true)
    NoticeService noticeService;

    @Autowired(required = true)
    JwtService jwtService;
}
