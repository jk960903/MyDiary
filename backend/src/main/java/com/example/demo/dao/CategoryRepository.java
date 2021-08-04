package com.example.demo.dao;

import com.example.demo.vo.Category.CategoryVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//카테고리 정의 CRUD 레포지토리
@Repository
public interface CategoryRepository extends JpaRepository<CategoryVO,Long> {
    @Query(value="insert into category(memberidx,category,legdate,isdeleted) value(:#{#categoryVO.memberidx},:#{#categoryVO.category},now(),1)" ,nativeQuery = true)
    public void AddCategory(CategoryVO categoryVO);

    @Query(value="update category set isdeleted=9 where idx =:#{#categoryIdx}" ,nativeQuery = true)
    public void DeleteCategory(Long categoryIdx);

    @Query(value="update category set category = :category where idx = :idx",nativeQuery = true )
    public void ChangeCategory(Integer category,Long idx);

    @Query(value="select * from diary.category where category.memberidx = :memberIdx",nativeQuery = true)
    public List<CategoryVO> GetCategoryList(Long memberIdx);
}
