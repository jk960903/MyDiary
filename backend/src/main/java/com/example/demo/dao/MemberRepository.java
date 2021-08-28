package com.example.demo.dao;

import com.example.demo.vo.Member.MemberVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<MemberVO, Long>{
    @Query(value="select * from Members where Members.ID = :ID" , nativeQuery = true)
    public List<MemberVO> findByID(String ID);

    @Query(value ="select * from Members where Members.Email = :email", nativeQuery=true)
    public List<MemberVO> findByEmail(String email);


    @Modifying
    @Query(value="Insert into members(ID,pwd,Email,phone,name,sex,isDeleted) "
            + "value(:#{#member.ID},:#{#member.pwd},:#{#member.email},:#{#member.phone},:#{#member.name},:#{#member.sex},0)" , nativeQuery=true)
    @Transactional
    public Integer MakeAccount(MemberVO member);


    @Query(value = "select * from members where members.ID = :ID and members.pwd = :password" ,nativeQuery= true)
    public List<MemberVO> Login(String ID,String password);

    @Query(value="select * from members where members.id=:ID" ,nativeQuery = true)
    public List<MemberVO> DuplicateID(String ID);

    @Query(value="select * from members where members.email=:email",nativeQuery = true)
    public List<MemberVO> DuplicateEmail(String email);
}
