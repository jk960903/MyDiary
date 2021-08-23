package com.example.demo.JWT;

import com.example.demo.vo.MemberVO;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;


@RequiredArgsConstructor
@Service
public class JwtService {


    //하드코드 되어있어서 수정이 필요합니다.
    private final String key ="thisisjung9keyipnida";


    //토큰생성성
   public String createLoginToken(MemberVO member,int auto){
        long curTime = System.currentTimeMillis();

        Map<String ,Object> headers = new HashMap<>();
        headers.put("typ","JWT");
        headers.put("alg","HS256");

        Map<String,Object> payload = new HashMap<>();
        payload.put("member",member);
        Long ExpiredTime;
        if(auto == 1){
            ExpiredTime = 1000*60L * 60L *24L * 30L;//한달
        }else{
            ExpiredTime = 1000*60L * 60L;//한시간
        }

        Date ext = new Date();
        ext.setTime(ext.getTime() + ExpiredTime);// 토큰 유효시간

        String jwt = Jwts.builder()
                .setHeader(headers)//헤더 설정
                .setClaims(payload)//Claims 설정
                .setSubject("user")//토큰 용도
                .setExpiration(ext)// 토큰 만료 시간 설정
                .signWith(SignatureAlgorithm.HS256,key.getBytes())
                .compact();//토큰생성

        return jwt;
    }

    //JWT 토큰에서 인증 정보 조회
    /*public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserID(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
   }*/
    public Map<String,Object> getUserID(String token){
        Map<String,Object> claimMap =null;
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey(key.getBytes("UTF-8"))
                    .parseClaimsJws(token)
                    .getBody();

            claimMap =claims;

            System.out.println("check");
        }catch(ExpiredJwtException e){
            System.out.println(e);
            return null;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
        return claimMap;

   }
   public Map<String,Object> requestAuthorization(HttpServletRequest request) throws IllegalAccessException{
       String token = request.getHeader("Authorization");
       if(token==null) return null;
       //token = token.substring(7);
       Map<String,Object> map = null;
       LinkedHashMap<String,Object> result =null;
       if(!token.equals("") && token !=null){
           map = this.getUserID(token);
           if(map!=null){
               result =(LinkedHashMap<String,Object>) map.get("member");
           }
       }else{
               throw new IllegalAccessException("No Token Or Token is Expired");
       }
       return result;
   }
    /*
    public String resolveToken(HttpServletRequest request){
        return request.getHeader("X-AUTH-TOKEN");
    }

    public boolean validateToken(String jwtToken){
        try{
            Jws<Claims> claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }*/

}
