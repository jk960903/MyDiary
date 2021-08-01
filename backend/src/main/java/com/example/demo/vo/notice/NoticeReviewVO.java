package com.example.demo.vo.notice;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class NoticeReviewVO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Seq;

    @Column(name="notice_idx")
    private Long  notice_idx;

    @Column(name="content")
    private String content;

    @Column(name="regdate")
    private Date regDate;

    @Column(name="isdeleted")
    private Byte isDeleted;

    @Column(name="memberidx")
    private Long memberidx;





}
