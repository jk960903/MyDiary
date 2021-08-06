package com.example.demo.vo.notice;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeReviewReqeust {
    private Long notice_idx;
    private Long member_idx;
    private String content;
}
