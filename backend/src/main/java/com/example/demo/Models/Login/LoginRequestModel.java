package com.example.demo.Models.Login;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestModel {
	private String userID;
	private String password;
	private int autologin;
}
