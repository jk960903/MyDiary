package com.example.demo.vo;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class VisitDetailVO {
    @Id
    private Long Seq;
}
