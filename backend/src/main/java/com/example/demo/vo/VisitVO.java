package com.example.demo.vo;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class VisitVO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idx;

    @Column(name="memberidx")
    private Long memberidx;

    @Column(name="latitude")
    private Float latitude;

    @Column(name="longitude")
    private Float longitude;

    @Column(name="visit_title")
    private String visit_title;

    @Column(name="star_point")
    private String star_point;

    @Column(name="address")
    private String address;

    @Column(name="address_road")
    private String address_road;

    @Column(name="visitdate")
    private Time visitdate;

    @Column(name="regdate")
    private Time regdate;

    @Column(name="viewtype")
    private Integer viewtype;

    @Column(name="isdelete")
    private Integer isdelete;

    @Column(name="thumburl")
    private String thumburl;

}
