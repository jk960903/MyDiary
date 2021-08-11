package com.example.demo.vo.notice;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangeNoticeReviewVO {
    private Long notice_seq;
    private Long member_seq;
    private Long review_seq;
    private String content;
}
