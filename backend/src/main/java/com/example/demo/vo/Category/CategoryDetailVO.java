package com.example.demo.vo.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="category_detail")
public class CategoryDetailVO {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idx;

    @Column(name="categoryname")
    private String categoryname;

    @Column(name="isdeleted")
    private Integer isdeleted;

}
