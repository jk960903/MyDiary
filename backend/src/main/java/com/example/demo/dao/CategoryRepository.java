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
    @Transactional
    @Modifying
    @Query(value="insert into category(memberidx,category,legdate,isdeleted) value(:#{#categoryVO.memberidx},:#{#categoryVO.category},now(),1)" ,nativeQuery = true)
    public void AddCategory(CategoryVO categoryVO);

    @Modifying
    @Query(value="update category set isdeleted=9 where idx =:#{#deleteCategoryRequest.idx}" ,nativeQuery = true)
    public void DeleteCategory(DeleteCategoryRequest deleteCategoryRequest);

    @Modifying
    @Query(value="update category set category = :category where idx = :idx",nativeQuery = true )
    public void UpdateCategory(Integer category,Long idx);

    @Query(value="select category.idx , category_detail.categoryname , category.regdate , category.isdeleted" +
            "from category" +
            "inner join category_detail on category.category = category_detail.idx where category.memberidx = :memberIdx",nativeQuery = true)
    public List<CategoryVO> GetCategoryList(Long memberIdx);

    @Query
    public List<CategoryVO> findCategoryByIdx(Long Idx);
}
