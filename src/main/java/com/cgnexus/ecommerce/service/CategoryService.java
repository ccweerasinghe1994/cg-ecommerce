package com.cgnexus.ecommerce.service;

import com.cgnexus.ecommerce.payload.ApiResponse;
import com.cgnexus.ecommerce.payload.CategoryDto;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface CategoryService {

    ApiResponse<List<CategoryDto>> getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);

    ApiResponse<CategoryDto> createCategory(CategoryDto category);

    ApiResponse<String> deleteCategory(Long categoryId) throws ResponseStatusException;

    ApiResponse<CategoryDto> updateCategory(Long categoryId, CategoryDto categoryDto);
}
