package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeReviewService {
    @Autowired
    NoticeReviewRepository noticeReviewRepository;
}
