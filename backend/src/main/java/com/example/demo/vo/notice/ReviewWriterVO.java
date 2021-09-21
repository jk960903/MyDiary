package com.example.demo.vo.notice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="members")
@NoArgsConstructor
@AllArgsConstructor
public class ReviewWriterVO {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    @Column(name="idx")
    private Long idx;

    @Column(name="id")
    private String ID;

}
