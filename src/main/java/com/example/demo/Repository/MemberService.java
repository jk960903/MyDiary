package com.example.demo.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.VO.*;
@Service
public class MemberService {
	
	@Autowired(required=true)
	private MemberRepository memberRepository;
	
	
	public MemberVO findByID(String id) {
		return memberRepository.findByID(id);
	}
	
	public MemberVO findByEmail(String Email) {
		return memberRepository.findByEmail(Email);
	}
	

	public Integer MakeAccount(MemberVO member) {
		return memberRepository.MakeAccount(member);
	}
}
