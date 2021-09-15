package com.example.demo.dao;

import com.example.demo.dto.Member.FindMemberEmailRequest;
import com.example.demo.vo.Login.LoginRequestVO;
import com.example.demo.vo.Member.MemberVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<MemberVO, Long>{

    //Duplicate email And 아이디 찾기
    public List<MemberVO> findByEmail(String email);

    //로그인용
    public List<MemberVO> findByIDAndPwd(String id,String pwd);

    //DuplicateID로 쓰임
    public List<MemberVO> findByID(String ID);


}
