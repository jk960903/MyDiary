package com.example.demo.dao;

import com.example.demo.vo.Category.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired(required = true)
    private  CategoryRepository categoryRepository;


    public void AddCategory(CategoryVO categoryVO){
        categoryRepository.AddCategory(categoryVO);
    }


    public CategoryVO DeleteCategory(Long categoryIdx){
        CategoryVO findCategoryVO = null;
        try{
            //먼저 삭제할 것이 있는지 확인해주고
            findCategoryVO = categoryRepository.findCategoryByIdx(categoryIdx).get(0);
        }catch(NullPointerException e){
            //없다면 null처리
            findCategoryVO = null;
        }finally {
            if(findCategoryVO != null){
                categoryRepository.DeleteCategory(categoryIdx);
            }
        }
        return findCategoryVO;
    }


    public void ChangeCategory(Integer categorynum,Long cateogryIdx){
        categoryRepository.ChangeCategory(categorynum,cateogryIdx);
    }


    public List<CategoryVO> getCategoryList(Long memberIdx){
        return categoryRepository.GetCategoryList(memberIdx);
    }

    public List<CategoryVO> findCategoryByIdx(Long idx){
        return categoryRepository.findCategoryByIdx(idx);
    }
}
