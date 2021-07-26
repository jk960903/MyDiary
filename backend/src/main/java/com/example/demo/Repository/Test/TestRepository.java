package com.example.demo.Repository.Test;

import com.example.demo.VO.Notice.NoticeDetailVO;
import com.example.demo.VO.Notice.NoticeVO;
import com.example.demo.VO.TestVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<TestVO,Long> {
    @Query(value="select * from test", nativeQuery=true)
    public List<TestVO> GetTest();

}
