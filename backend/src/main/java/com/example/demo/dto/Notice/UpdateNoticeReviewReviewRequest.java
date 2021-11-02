package com.example.demo.dto.Notice;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateNoticeReviewReviewRequest {
    private Long idx;
    private Long memberidx;
    private String content;

    public boolean CheckValidate() throws NullPointerException{
        if(content==null || content.equals("") || this.memberidx <=0) throw new NullPointerException("BAD REQEUST");
        return true;
    }

    public boolean CheckLoginValidate(Long member_idx) throws IllegalAccessException{
        if(this.memberidx == member_idx) return true;
        else throw new IllegalAccessException("No Token Or Token is Expired");
    }
}
