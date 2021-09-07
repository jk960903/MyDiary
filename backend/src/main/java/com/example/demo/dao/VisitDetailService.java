package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitDetailService {

    private VisitDetailRepository visitDetailRepository;

    @Autowired
    public VisitDetailService(VisitDetailRepository visitDetailRepository){
        this.visitDetailRepository=visitDetailRepository;
    }
}
