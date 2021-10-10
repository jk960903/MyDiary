package com.example.demo.dao;

import com.example.demo.controller.LoginController;
import com.example.demo.dto.Member.FindMemberEmailRequest;
import com.example.demo.vo.Login.LoginRequestVO;
import com.example.demo.vo.Member.MemberVO;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class MemberService  {


    @Autowired
    private MemberRepository memberRepository;



    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public List<MemberVO> findByID(String id) throws NullPointerException,Exception{
        if(id==null || id.equals("")){
            throw new NullPointerException("BAD REQUEST");
        }
        return memberRepository.findByID(id);
    }


    public MemberVO findByEmail(FindMemberEmailRequest email) throws IndexOutOfBoundsException {
        MemberVO member=new MemberVO();
        try{
            member=memberRepository.findByEmail(email.getEmail()).get(0);
        }catch(IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("NO DATA");
        }catch(Exception e){
            e.printStackTrace();
        }
        return member;
    }

    public MemberVO Login(LoginRequestVO loginRequestVO) throws IndexOutOfBoundsException,Exception{
        MemberVO member=null;
        try{
            member=memberRepository.findByIDAndPwd(loginRequestVO.getUserID(), loginRequestVO.getPassword()).get(0);
        }catch(IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("NO DATA");
        }catch(Exception e){
            throw new Exception("INTERNAL SERVER ERROR");
        }
        return member;
    }

    public Integer MakeAccount(MemberVO member) throws Exception {
        try{
            memberRepository.save(member);
        }catch (Exception e){
            throw new Exception("INTERVAL SERVER ERROR");
        }
        return 1;
    }


    public boolean isDuplicated(String ID) throws Exception{
        MemberVO memberVO;
        try{
            memberVO=memberRepository.findByID(ID).get(0);
        }catch(IndexOutOfBoundsException e){
            //검색 결과가 나오지 않는다면 indexoutofbounds 가 발생한다. 즉 중복되지 않았다
            return true;
        }catch(Exception e){
            throw new Exception("INTERNAL SERVER ERROR");
        }
        //익셉션이 발생하지 않는다면 중복
        return false;
    }

    public boolean isDuplicatedEmail(String email) throws Exception{
        MemberVO memberVO;
        try{
            memberVO = memberRepository.findByEmail(email).get(0);
        }catch(IndexOutOfBoundsException e){
            return true;
        }catch(Exception e){
            throw new Exception("INTERNAL SERVER ERROR");
        }
        return false;
    }

    @Transactional
    @Modifying
    public MemberVO UpdateAccount(MemberVO member) throws Exception{
        MemberVO findmember = null;
        try{
            findmember = memberRepository.findByID(member.getID()).get(0);
            findmember.setPhone(member.getPhone());
            findmember.setEmail(member.getEmail());
            member= memberRepository.save(findmember);

        }catch(Exception e){
            throw new Exception("INTERNAL SERVER ERROR");
        }
        return member;
    }





}
