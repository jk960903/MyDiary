package com.example.demo.Repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.VO.*;

@Repository
public interface MemberRepository extends JpaRepository<MemberVO, Long>{
	@Query(value="select * from Members where Members.ID = :ID" , nativeQuery = true)
	public MemberVO findByID(String ID);
	
	@Query(value ="select * from Members where Members.Email = :email", nativeQuery=true)
	public MemberVO findByEmail(String email);
	
	@Query(value="Insert into Member(ID,pwd,Email,phone,name,sex,isDeleted) "
			+ "values(#{member.ID},#{member.pwd},#{member.email},#{member.phone},#{member.name},#{member.sex},0)" , nativeQuery=true)
	public Integer MakeAccount(MemberVO member);
	
}


