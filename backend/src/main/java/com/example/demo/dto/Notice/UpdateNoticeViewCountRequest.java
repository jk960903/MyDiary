package com.example.demo.dto.Notice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateNoticeViewCountRequest {
    private Integer viewCount;
    private Long idx;


}
