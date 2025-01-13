package com.cgnexus.ecommerce.controllers;

import com.cgnexus.ecommerce.payload.ApiResponse;
import com.cgnexus.ecommerce.payload.CategoryDTO;
import com.cgnexus.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cgnexus.ecommerce.config.AppConstants.*;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/public/categories")
    public ResponseEntity<ApiResponse<List<CategoryDTO>>> getAllCategories(
            @RequestParam(name = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize,
            @RequestParam(name = "pageNumber", defaultValue = DEFAULT_PAGE_NUMBER) Integer pageNumber,
            @RequestParam(name = "sortBy", defaultValue = DEFAULT_SORT_BY) String sortBy,
            @RequestParam(name = "sortDirection", defaultValue = DEFAULT_SORT_DIRECTION) String sortDirection
    ) {
        ApiResponse<List<CategoryDTO>> allCategories = categoryService.getAllCategories(pageNumber, pageSize, sortBy, sortDirection);
        return ResponseEntity.ok(allCategories);
    }

    @PostMapping("/admin/categories")
    public ResponseEntity<ApiResponse<CategoryDTO>> addCategory(@RequestBody @Valid CategoryDTO categoryDto) {
        ApiResponse<CategoryDTO> response = categoryService.createCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<ApiResponse<String>> deleteCategory(@PathVariable Long categoryId) {
        ApiResponse<String> response = categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/admin/categories/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryDTO>> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryDTO categoryDto) {
        ApiResponse<CategoryDTO> categoryDtoApiResponse = categoryService.updateCategory(categoryId, categoryDto);
        return ResponseEntity.ok(categoryDtoApiResponse);
    }
}
