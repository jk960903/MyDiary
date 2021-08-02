package com.example.demo.vo;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CategoryVO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idx;

    @Column(name="memberidx")
    private Long memberidx;

    @Column(name="category")
    private Byte category;

    @Column(name="regdate")
    private Date regdate;

    @Column(name="isdeleted")
    private Byte isdeleted;

}
