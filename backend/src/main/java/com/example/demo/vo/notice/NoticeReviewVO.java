package com.example.demo.vo.notice;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class NoticeReviewVO {
    @Id
    public Long Seq;
}
