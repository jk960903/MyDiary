package com.example.demo.dto.Notice;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteNoticeDetailRequest {
    private Long idx;

    public boolean CheckValidate() throws NullPointerException{
        if(this.idx==null || this.idx<=0) throw new NullPointerException("BAD REQEUST");
        return true;
    }
}
