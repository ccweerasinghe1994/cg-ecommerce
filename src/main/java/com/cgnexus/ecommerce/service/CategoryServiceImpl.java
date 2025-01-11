package com.cgnexus.ecommerce.service;

import com.cgnexus.ecommerce.model.Category;
import com.cgnexus.ecommerce.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    private final CategoryRepository categoryRepository;

    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) throws ResponseStatusException {

        if (!categoryRepository.existsById(categoryId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }

        categoryRepository.deleteById(categoryId);

        return "Category with id " + categoryId + " deleted successfully";
    }

    @Override
    public String updateCategory(Long categoryId, Category category) throws ResponseStatusException {
        Category categoryToUpdate = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        if (category.getCategoryName() != null) {
            categoryToUpdate.setCategoryName(category.getCategoryName());
        }

        categoryRepository.save(categoryToUpdate);

        return "Category with id " + categoryId + " updated successfully";
    }
}
