package com.example.demo.dto.Notice;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetNoticeReviewRequest {
    private Long noticeIdx;
}
