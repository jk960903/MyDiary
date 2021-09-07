package com.example.demo.vo.Login;

import lombok.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public boolean checkValidate() throws NullPointerException{
        if(this.userID == null || this.userID.equals("")||this.password==null||this.password.equals("")||this.autologin.equals("")||this.autologin==null) throw new NullPointerException("BAD REQUEST");
        return true;
    }

    public boolean checkRegex(){
        Pattern pattern = Pattern.compile("^[a-z0-9]*$");
        Matcher matcher = pattern.matcher(this.userID);
        if(!matcher.find()){
            return false;
        }
        return true;
    }
}
