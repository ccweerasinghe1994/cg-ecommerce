package com.cgnexus.ecommerce.service;

import com.cgnexus.ecommerce.exception.ApiException;
import com.cgnexus.ecommerce.exception.ResourceNotFoundException;
import com.cgnexus.ecommerce.model.Category;
import com.cgnexus.ecommerce.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        List<Category> all = categoryRepository.findAll();

        if (all.isEmpty()) {
            throw new ApiException("No categories found", HttpStatus.NO_CONTENT);
        }

        return all;
    }

    @Override
    public void createCategory(Category category) {

        List<Category> byCategoryName = categoryRepository.findByCategoryName(category.getCategoryName());

        if (!byCategoryName.isEmpty()) {
            throw new ApiException("Category with %s already exists".formatted(category.getCategoryName()), HttpStatus.CONFLICT);
        }
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) throws ResponseStatusException {

        if (!categoryRepository.existsById(categoryId)) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
            throw new ResourceNotFoundException("Category", "categoryId", categoryId);
        }

        categoryRepository.deleteById(categoryId);

        return "Category with id " + categoryId + " deleted successfully";
    }

    @Override
    public String updateCategory(Long categoryId, Category category) throws ResponseStatusException {
        Category categoryToUpdate = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        if (category.getCategoryName() != null) {
            categoryToUpdate.setCategoryName(category.getCategoryName());
        }

        categoryRepository.save(categoryToUpdate);

        return "Category with id " + categoryId + " updated successfully";
    }
}
