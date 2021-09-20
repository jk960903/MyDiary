package com.example.demo.dto.Notice;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteNoticeReviewReviewRequest {

    private Long idx;
}
