package com.example.demo.dao;

import com.example.demo.vo.VisitDetailVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitDetailRepository extends JpaRepository<VisitDetailVO,Long> {
}
