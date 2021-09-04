package com.example.demo.vo.notice;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
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

	public boolean IsValidate() throws NullPointerException{
		if(content.equals("") || content==null|| noticeidx==null||noticeidx<=0) throw new NullPointerException("BAD REQUEST");
		return true;
	}
}	
