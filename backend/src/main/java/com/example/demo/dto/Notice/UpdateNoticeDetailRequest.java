package com.example.demo.dto.Notice;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateNoticeDetailRequest {
    private Long idx;
    private String content;

    public boolean CheckValidate() throws NullPointerException{
        if(this.idx<=0 || this.idx==null || this.content.equals("") ||this.content==null){
            throw new NullPointerException("BAD REQUEST");
        }
        return true;
    }

}
