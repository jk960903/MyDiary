package com.example.demo.Repository.Test;

import com.example.demo.VO.TestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    TestRepository testRepository;

    public List<TestVO> DBTest(){
        return testRepository.GetTest();
    }
}
