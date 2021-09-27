package com.example.demo.vo.MyInfo;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

}
