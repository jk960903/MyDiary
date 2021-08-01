package com.example.demo.controller;

import com.example.demo.JWT.JwtService;
import com.example.demo.dao.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/Category")
public class CategoryController {
    @Autowired(required = true)
    JwtService jwtService;

    @Autowired(required = true)
    CategoryService categoryService;
}
