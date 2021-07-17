package com.example.demo.VO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="members")
@Builder
public class MemberVO {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//Primary Key
	private Long index;
	//ID
	private String ID;
	//password
	private String pwd;
	//Email
	private String Email;
	//전화번호
	private String phone;
	//이름
	private String name;
	//성별
	private Byte sex;
	//삭제된지 확인 추후인덱스를 위해 사용을 예정 삭제됬으면 9
	private Byte isDeleted;
}
