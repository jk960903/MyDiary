package com.example.demo.dto.Category;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteCategoryRequest {
    private Long idx;
    private Long memberIdx;

}
