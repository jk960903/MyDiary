package com.example.demo.dao;

import com.example.demo.vo.Category.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }

    public void AddCategory(CategoryVO categoryVO) throws Exception{
        try{
            categoryRepository.AddCategory(categoryVO);
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("INTERVAL SERVER ERROR");
        }


    }


    public CategoryVO DeleteCategory(Long categoryIdx) throws IndexOutOfBoundsException,Exception{
        CategoryVO findCategoryVO = null;
        try{
            //먼저 삭제할 것이 있는지 확인해주고
            findCategoryVO = categoryRepository.findCategoryByIdx(categoryIdx).get(0);
            if(findCategoryVO != null){
                categoryRepository.DeleteCategory(categoryIdx);
            }
        }catch(IndexOutOfBoundsException e){
            //없다면 null처리
            throw new IndexOutOfBoundsException("NO DATA BAD REQUEST");
        }catch(Exception e){
            throw new Exception("INTERVAL SERVER ERROR");
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
