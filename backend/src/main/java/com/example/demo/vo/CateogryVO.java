package com.example.demo.vo;

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
public class CateogryVO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idx;


}
