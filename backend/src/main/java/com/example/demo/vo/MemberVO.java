package com.example.demo.vo;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="members")
@Builder
public class MemberVO {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	//Primary Key
	private Long idx;
	//ID
	@Column
	private String ID;
	//password
	@Column
	private String pwd;
	//Email
	@Column
	private String email;
	//전화번호
	@Column
	private String phone;
	//이름
	@Column
	private String name;
	//성별
	@Column
	private Byte sex;
	
	@Column
	private Byte isdeleted;
	
}
