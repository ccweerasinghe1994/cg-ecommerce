package com.cgnexus.ecommerce.service;

import com.cgnexus.ecommerce.payload.ApiResponse;
import com.cgnexus.ecommerce.payload.CategoryDTO;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface CategoryService {

    ApiResponse<List<CategoryDTO>> getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);

    ApiResponse<CategoryDTO> createCategory(CategoryDTO category);

    ApiResponse<String> deleteCategory(Long categoryId) throws ResponseStatusException;

    ApiResponse<CategoryDTO> updateCategory(Long categoryId, CategoryDTO categoryDto);
}
