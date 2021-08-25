package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddNoticeReviewReqeust {
    private Long notice_idx;
    private Long member_idx;
    private String content;

    public boolean CheckValidate(){
        if(content==null || content.equals("") || this.notice_idx <=0 || this.member_idx <=0) throw new NullPointerException("BAD REQEUST");
        return true;
    }
    public boolean CheckLoginValidate(Long member_idx) throws IllegalAccessException{
        if(this.member_idx == member_idx) return true;
        else throw new IllegalAccessException("No Token Or Token is Expired");
    }
}
