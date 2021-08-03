package com.example.demo.dao;

import com.example.demo.vo.VisitVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends JpaRepository<VisitVO,Long> {

}
