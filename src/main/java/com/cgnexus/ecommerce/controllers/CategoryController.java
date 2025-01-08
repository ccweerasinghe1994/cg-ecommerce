package com.cgnexus.ecommerce.controllers;

import com.cgnexus.ecommerce.model.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {
    private final List<Category> categories = new ArrayList<>();


    @GetMapping("/public/categories")
    public List<Category> getAllCategories(){
        return categories;
    }

    @PostMapping("/public/categories")
    public String addCategory(@RequestBody Category category){
        categories.add(category);
        return "category added successfully";
    }
}
