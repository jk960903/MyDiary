package com.example.demo.vo;

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
	//성별
	@Column
	private Byte sex;
	
	@Column
	private Byte isdeleted;


	/*@ElementCollection(fetch=FetchType.EAGER)
	@Builder.Default
	private List<String> roles = new ArrayList<>();
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles.stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return null;
	}


	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}*/
}
