package com.example.demo.vo.notice;

import jdk.jfr.Name;
import lombok.*;

import javax.persistence.Access;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeReviewResult {
    private NoticeReviewVO noticeReviewVO;
    private List<NoticeReviewReviewVO> noticeReviewReviewVOList;
}
