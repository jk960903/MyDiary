package com.example.demo.JWT;

import com.example.demo.vo.MemberVO;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class JwtService {

    //하드코드 되어있어서 수정이 필요합니다.
    private final String key ="thisisjustencrypkey";

    public String createLoginToken(MemberVO member){
        long curTime = System.currentTimeMillis();

        Map<String ,Object> headers = new HashMap<>();
        headers.put("typ","JWT");
        headers.put("alg","HS256");

        Map<String,Object> payload = new HashMap<>();
        payload.put("member",member);

        Date ext = new Date();
        ext.setTime(curTime + (1000*60*60*24*30));// 토큰 유효시간

        String jwt = Jwts.builder()
                .setHeader(headers)//헤더 설정
                .setClaims(payload)//Claims 설정
                .setSubject("user")//토큰 용도
                .setExpiration(ext)// 토큰 만료 시간 설정
                .signWith(SignatureAlgorithm.HS256,key.getBytes())//암호화 sign key 를 뭘로바꾸면 좋을까
                .compact();//토큰생성

        return jwt;
    }

    public MemberVO getUserID(String jwt){
        Map<String,Object> claimmap = null;
        Claims claims;
        MemberVO member=null;
        try{
            claims=Jwts.parser()
                    .setSigningKey(key.getBytes("UTF-8"))
                    .parseClaimsJws(jwt)
                    .getBody();

            claimmap = claims;

            member = (MemberVO)claimmap.get("user");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch(ExpiredJwtException e){//토큰만료
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }
        return member;
    }
}
