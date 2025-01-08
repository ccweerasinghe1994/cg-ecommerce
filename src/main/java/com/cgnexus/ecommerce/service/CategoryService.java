package com.cgnexus.ecommerce.service;

import com.cgnexus.ecommerce.model.Category;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();
    void createCategory(Category category);

    String deleteCategory(Long categoryId) throws ResponseStatusException;

    String updateCategory(Long categoryId, Category category);
}
