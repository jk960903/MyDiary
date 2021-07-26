package com.example.demo.vo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestVO {
    private String userID;
    private String password;
    private String autologin;
}
