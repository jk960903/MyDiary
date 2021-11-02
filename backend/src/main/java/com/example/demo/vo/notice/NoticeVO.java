package com.example.demo.vo.notice;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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

	@Column(name="isdeleted")
	private Integer isDeleted;
	public boolean CheckValidate(){
		if(writer==null || writer.equals("") || viewcount<0) throw new NullPointerException("BAD REQEUST");
		return true;
	}

	public Date SetToday(){
		long time = System.currentTimeMillis();
		this.regdate = new Date(time);

		return this.regdate;
	}
	/*나중에 수정이 필요함 -> 작성자 컬럼을 member idx 로 바꾸고 나중에 이 작성자의 권한레벨을 보는것도 나쁘지 않을듯 싶습니다.
	public boolean CheckLoginValidate(Long member_idx) throws IllegalAccessException{
		if(this.memberidx == member_idx) return true;
		else throw new IllegalAccessException("No Token Or Token is Expired");
	}*/
	
}
