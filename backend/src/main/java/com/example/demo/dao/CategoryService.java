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

    /*public void AddCategory(CategoryVO categoryVO) throws Exception{
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
            findCategoryVO.setIsdeleted(9);
            categoryRepository.save(findCategoryVO);
        }catch(IndexOutOfBoundsException e){
            //없다면 null처리
            throw new IndexOutOfBoundsException("NO DATA BAD REQUEST");
        }catch(Exception e){
            throw new Exception("INTERVAL SERVER ERROR");
        }
        return findCategoryVO;
    }


    public void UpdateCategory(Integer categorynum,Long cateogryIdx) throws Exception{
        CategoryVO categoryVO = null;
        try{
            categoryVO = categoryRepository.findCategoryByIdx(cateogryIdx).get(0);
            categoryRepository.UpdateCategory(categorynum,cateogryIdx);
        }catch(IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("NO DATE");
        }
        catch(Exception e){
            throw new Exception("INTERNAL SERVER ERROR");
        }
    }


    public List<CategoryVO> getCategoryList(Long memberIdx) throws Exception{
        List<CategoryVO> list;
        try{
            list=categoryRepository.findCategoryByMemberidxAndIsdeleted(memberIdx,1);
        }catch(Exception e){
            throw new Exception("INTERNAL SERVER ERROR");
        }
        return list;
    }*/

}
