package com.example.demo.Repository;

import com.example.demo.VO.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MemberService {
	
	@Autowired(required=true)
	private MemberRepository memberRepository;
	
	
	public List<MemberVO> findByID(String id) {
		return memberRepository.findByID(id);
	}
	
	public List<MemberVO> findByEmail(String Email) {
		return memberRepository.findByEmail(Email);
	}
	

	public Integer MakeAccount(MemberVO member) {
		return memberRepository.MakeAccount(member);
	}
	
	public List<MemberVO> Login(String ID, String password) {
		return memberRepository.Login(ID, password);
	}
}
