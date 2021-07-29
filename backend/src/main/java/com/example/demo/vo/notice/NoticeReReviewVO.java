package com.example.demo.vo.notice;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class NoticeReReviewVO {
    @Id
    public Long Seq;
}
