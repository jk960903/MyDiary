package com.example.demo.vo.notice;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name="notice")
@Builder
public class NoticeVO {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idx;
	
	@Column(name="title")
	private String title;
	
	@Column(name="writer")
	private String writer;
	
	@Column(name="regdate")
	private Date regdate;
	
	@Column(name="viewcount")
	private Integer viewcount;
	
}
