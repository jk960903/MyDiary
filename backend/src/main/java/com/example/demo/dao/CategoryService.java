package com.example.demo.dao;

import com.example.demo.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired(required = true)
    private  CategoryRepository categoryRepository;


    public void AddCategory(CategoryVO categoryVO){
        categoryRepository.AddCategory(categoryVO);
    }


    public void DeleteCategory(Long categoryIdx){
        categoryRepository.DeleteCategory(categoryIdx);
    }


    public void ChangeCategory(Integer categorynum,Long cateogryIdx){
        categoryRepository.ChangeCategory(categorynum,cateogryIdx);
    }


    public List<CategoryVO> getCategoryList(Long memberIdx){
        return categoryRepository.findCategoryVOByMemberidx(memberIdx);
    }
}
