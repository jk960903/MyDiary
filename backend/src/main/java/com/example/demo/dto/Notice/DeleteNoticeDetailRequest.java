package com.example.demo.dto.Notice;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteNoticeDetailRequest {
    Long idx;

    public boolean CheckValidate() throws NullPointerException{
        if(idx==null || idx<=0) throw new NullPointerException("BAD REQEUST");
        return true;
    }
}
