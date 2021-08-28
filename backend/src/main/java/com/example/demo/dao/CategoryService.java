package com.example.demo.dao;

import com.example.demo.dto.Category.DeleteCategoryRequest;
import com.example.demo.vo.Category.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

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


    public CategoryVO DeleteCategory(DeleteCategoryRequest categoryIdx) throws IndexOutOfBoundsException,Exception{
        CategoryVO findCategoryVO = null;
        try{
            //먼저 삭제할 것이 있는지 확인해주고
            findCategoryVO = categoryRepository.findCategoryByIdx(categoryIdx.getIdx()).get(0);
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


    public List<CategoryVO> getCategoryList(Long memberIdx) throws Exception{
        List<CategoryVO> list;
        try{
            list=categoryRepository.GetCategoryList(memberIdx);
        }catch(Exception e){
            throw new Exception("INTERNAL SERVER ERROR");
        }
        return list;
    }

    public CategoryVO findCategoryByIdx(Long idx) throws IndexOutOfBoundsException , Exception{
        CategoryVO categoryVO = null;
        try{
            categoryVO = categoryRepository.findCategoryByIdx(idx).get(0);
        }catch(IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("NO DATA");
        }catch(Exception e){
            throw new Exception("INTERNAL SERVER ERROR");
        }
        return categoryVO;

    }
}
