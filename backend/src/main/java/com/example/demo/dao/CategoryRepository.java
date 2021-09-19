package com.example.demo.dao;

import com.example.demo.dto.Category.DeleteCategoryRequest;
import com.example.demo.vo.Category.CategoryVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

//카테고리 정의 CRUD 레포지토리
@Repository
public interface CategoryRepository extends JpaRepository<CategoryVO,Long> {

    public List<CategoryVO> findByMemberidxAndIsdeleted(Long memberIdx,Integer isDeleted);



    @Query
    public List<CategoryVO> findCategoryByIdx(Long Idx);
}
