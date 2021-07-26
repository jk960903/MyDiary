package com.example.demo.VO.Notice;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="notice_detail")
public class NoticeDetailVO {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idx;
	
	@Column(name ="content")
	private String content;
	
	@Column(name ="noticeidx")
	private Long noticeidx;
	
	@Column(name ="imageurl")
	private String imageurl;
}	
