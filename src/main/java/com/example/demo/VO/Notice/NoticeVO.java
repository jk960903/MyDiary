package com.example.demo.VO.Notice;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
