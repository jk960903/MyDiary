package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UpdateNoticeCountRequest {
    private Long idx;
    private Integer viewCount;

    public boolean checkValidate() throws NullPointerException{
        if(this.idx<=0 || this.idx==null) throw new NullPointerException("BAD REQUEST");
        else return true;
    }
}
