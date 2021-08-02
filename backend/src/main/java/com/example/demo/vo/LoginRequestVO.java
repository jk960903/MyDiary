package com.example.demo.vo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//View로부터 login 넘어오는 클래스
public class LoginRequestVO {
    private String userID;
    private String password;
    private String autologin;
}
