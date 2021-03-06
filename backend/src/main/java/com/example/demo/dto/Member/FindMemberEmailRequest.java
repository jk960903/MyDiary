package com.example.demo.dto.Member;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindMemberEmailRequest {

    @Id
    private String email;

    public boolean IsValidateEmail(){

        Pattern pattern = Pattern.compile("^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$");
        Matcher matcher = pattern.matcher(this.email);
        if(!matcher.find()){
            return false;
        }
        return true;
    }
}
