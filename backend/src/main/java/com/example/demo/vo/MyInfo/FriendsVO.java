package com.example.demo.vo.MyInfo;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class FriendsVO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idx;

    private Long memberidx;

    private Long friendsidx;

    private Time regDate;

    private Integer isdeleted;


}
