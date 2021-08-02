package com.example.demo.vo.notice;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

public class NoticeReviewReviewVO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idx;

    @Column(name ="content")
    private String content;

    @Column(name ="noticeidx")
    private Long noticeidx;

    @Column(name ="imageurl")
    private String imageurl;


}
