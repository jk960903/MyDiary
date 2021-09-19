package com.example.demo.dto.Notice;

import lombok.*;

//solid 원칙때문에 새로 팜
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteNoticeReviewRequest {
    private Long idx;
    private Long member_idx;

    public boolean CheckValidate(){
        if(this.member_idx <=0||idx<=0) throw new NullPointerException("BAD REQEUST");
        return true;
    }
    public boolean CheckLoginValidate(Long member_idx) throws IllegalAccessException{
        if(this.member_idx.equals(member_idx)) return true;
        else throw new IllegalAccessException("No Token Or Token is Expired");
    }
}
