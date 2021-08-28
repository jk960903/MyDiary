package com.example.demo.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewNoticeDetail {
    private String writer;
    private String title;
    private String content;
    private Date regdate;
    private Integer viewcount;
}
