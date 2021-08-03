package com.example.demo.JWT;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtReqeustFilter extends GenericFilterBean {

    @Autowired(required=true)
    private final JwtService jwtService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /*//헤더에서 jwt를 받아오고
        String token  = jwtService.resolveToken((HttpServletRequest) request);
        //토큰의 유효 확인
        if(token!=null && jwtService.validateToken(token)){
            //유효하다면 유저 정보를 받아옴
            Authentication authentication =jwtService.getAuthentication(token);
            //객체 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request,response);
         */
    }
}
