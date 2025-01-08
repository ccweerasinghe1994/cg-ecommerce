package com.cgnexus.ecommerce.service;

import com.cgnexus.ecommerce.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final List<Category> categories = new ArrayList<>();

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        Long id = (long) (categories.size() + 1);
        category.setCategoryId(id);
        categories.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId) throws ResponseStatusException {

        Category category = categories.stream().filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        categories.remove(category);

        return "Category with id " + categoryId + " deleted successfully";
    }

    @Override
    public String updateCategory(Long categoryId, Category category) throws ResponseStatusException {
        Category categoryToUpdate = categories.stream().filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        if (category.getCategoryName() != null) {
            categoryToUpdate.setCategoryName(category.getCategoryName());
        }

        return "Category with id " + categoryId + " updated successfully";
    }
}
