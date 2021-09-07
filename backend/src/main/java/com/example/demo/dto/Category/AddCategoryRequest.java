package com.example.demo.dto.Category;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddCategoryRequest {
    private Integer categoryidx;
    private Long memberidx;

    public boolean isValidate() throws NullPointerException{
        if(this.categoryidx == null || this.categoryidx <=0 || this.memberidx ==null || this.memberidx <=0) throw new NullPointerException("BAD REQUEST");
        return true;
    }


}
