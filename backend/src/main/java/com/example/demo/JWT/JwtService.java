package com.example.demo.JWT;

import com.example.demo.vo.MemberVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
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
import java.util.Map;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
@Service
public class JwtService {


    //하드코드 되어있어서 수정이 필요합니다.
    private final String key ="thisisjung9keyipnida";

    private final UserDetailsService userDetailsService;

    //토큰생성성
   public String createLoginToken(MemberVO member){
        long curTime = System.currentTimeMillis();

        Map<String ,Object> headers = new HashMap<>();
        headers.put("typ","JWT");
        headers.put("alg","HS256");

        Map<String,Object> payload = new HashMap<>();
        payload.put("member",member);
        Long ExpiredTime = 1000*60L * 60L *24L;
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
    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserID(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
   }

    public String getUserID(String token){
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
   }

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
    }

}
