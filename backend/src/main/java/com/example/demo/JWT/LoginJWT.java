package com.example.demo.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Jwts;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class LoginJWT {

    private final String key ="thisisjustencrypkey";

    public String createToken(Long id){
        Map<String ,Object> headers = new HashMap<>();
        headers.put("typ","JWT");
        headers.put("alg","HS256");

        Map<String,Object> payload = new HashMap<>();
        payload.put("member_index",id);

        Date ext = new Date();
        ext.setTime(ext.getTime() + (1000*60*60*24*30));// 토큰 유효시간

        String jwt = Jwts.builder()
                .setHeader(headers)//헤더 설정
                .setClaims(payload)//Claims 설정
                .setSubject("user")//토큰 용도
                .setExpiration(ext)// 토큰 만료 시간 설정
                .signWith(SignatureAlgorithm.HS256,key.getBytes())//암호화 sign key 를 뭘로바꾸면 좋을까
                .compact();//토큰생성

        return jwt;
    }
}
