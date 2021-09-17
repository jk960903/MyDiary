package com.example.demo.vo.notice;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeResultVO {
    NoticeVO noticeVO;
    NoticeDetailVO noticeDetailVO;
    List<NoticeReviewVO> noticeReviewVO;
    List<NoticeReviewReviewVO> noticeReviewReviewVO;
}
