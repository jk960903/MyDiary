package com.example.demo.vo.notice;

import lombok.*;

import java.util.List;
/*
* 공지사항 페이지 return result
*
* */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeResultVO {
    NoticeVO noticeVO;
    NoticeDetailVO noticeDetailVO;
    List<NoticeReviewResult> noticeReviewVO;
}
