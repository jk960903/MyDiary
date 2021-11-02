package com.example.demo.dto.Member;

import lombok.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckDuplicateEmailRequest {//상속으로 함수하나 정의하는건 ?
    private String email;

    public boolean IsValidateEmail() throws NullPointerException{
        if(this.email == null ) throw new NullPointerException("이메일을 입력해주세요");
        Pattern pattern = Pattern.compile("^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$");
        Matcher matcher = pattern.matcher(this.email);
        if(!matcher.find()){
            throw new NullPointerException("이메일 형식으로 입력해주세요");
        }
        return true;
    }
}
