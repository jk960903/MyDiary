package com.example.demo.vo.Member;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import java.util.regex.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="members")
@Builder
public class MemberVO{
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
	//성별( 남자는 1 여자는 2)
	@Column
	private Byte sex;
	//삭제 9 기본 1
	@Column
	private Byte isdeleted;


	public boolean CheckValidate() throws NullPointerException{
		if(ID==null || pwd == null || email == null || phone == null || name == null || sex <=0 || isdeleted <0) throw new NullPointerException("BAD REQUEST");
		return true;
	}


	//유효아이디인지 확인메서드
	public boolean IsValidateID(){
		Pattern pattern = Pattern.compile("[ !@#$%^&*(),.?\\\":{}|<>]");
		Matcher matcher = pattern.matcher(ID);
		if(matcher.find()){
			return false;
		}
		return true;
	}

}
