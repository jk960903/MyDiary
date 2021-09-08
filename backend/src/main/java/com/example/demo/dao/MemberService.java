package com.example.demo.dao;

import com.example.demo.dto.Member.FindMemberEmailRequest;
import com.example.demo.vo.Member.MemberVO;

import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MemberService  {


    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    public List<MemberVO> findByID(String id) throws NullPointerException{
        if(id==null || id.equals("")) throw new NullPointerException("BAD REQUEST");
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


    public Integer MakeAccount(MemberVO member) throws Exception {
        try{
            memberRepository.MakeAccount(member);
        }catch (Exception e){
            throw new Exception("INTERVAL SERVER ERROR");
        }
        return 1;
    }

    public List<MemberVO> Login(String ID, String password) {
        return memberRepository.Login(ID, password);
    }

    public boolean isDuplicated(String ID) throws Exception{
        MemberVO memberVO;
        try{
            memberVO=memberRepository.DuplicateID(ID).get(0);
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
            memberVO = memberRepository.DuplicateEmail(email).get(0);
        }catch(IndexOutOfBoundsException e){
            return true;
        }catch(Exception e){
            throw new Exception("INTERNAL SERVER ERROR");
        }
        return false;
    }
    public void UpdateAccount(MemberVO member) throws Exception{
        try{
            memberRepository.UpdateAccount(member);

        }catch(Exception e){
            throw new Exception("INTERNAL SERVER ERROR");
        }

    }

}
