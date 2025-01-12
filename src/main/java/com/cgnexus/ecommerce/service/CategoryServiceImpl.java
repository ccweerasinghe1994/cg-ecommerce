package com.cgnexus.ecommerce.service;

import com.cgnexus.ecommerce.exception.ApiException;
import com.cgnexus.ecommerce.exception.ResourceNotFoundException;
import com.cgnexus.ecommerce.model.Category;
import com.cgnexus.ecommerce.payload.ApiResponse;
import com.cgnexus.ecommerce.payload.CategoryDto;
import com.cgnexus.ecommerce.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final ModelMapper modelMapper;

    private final CategoryRepository categoryRepository;

    @Override
    public ApiResponse<List<CategoryDto>> getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Category> categoryPages = categoryRepository.findAll(pageable);
        List<Category> all = categoryPages.getContent();

        if (all.isEmpty()) {
            throw new ApiException("No categories found", HttpStatus.NO_CONTENT);
        }

        List<CategoryDto> list = all.stream().map(category -> modelMapper.map(category, CategoryDto.class)).toList();

        return ApiResponse
                .<List<CategoryDto>>builder()
                .content(list)
                .lastPage(categoryPages.isLast())
                .pageNumber(categoryPages.getNumber())
                .pageSize(categoryPages.getSize())
                .totalElements(categoryPages.getTotalElements())
                .totalPages(categoryPages.getTotalPages())
                .build();
    }


    @Override
    public ApiResponse<CategoryDto> createCategory(CategoryDto categoryDto) {

        List<Category> byCategoryName = categoryRepository.findByCategoryName(categoryDto.getCategoryName());

        if (!byCategoryName.isEmpty()) {
            throw new ApiException("Category with %s already exists".formatted(categoryDto.getCategoryName()),
                    HttpStatus.CONFLICT);
        }
        Category category = modelMapper.map(categoryDto, Category.class);
        Category saved = categoryRepository.save(category);

        return ApiResponse.<CategoryDto>builder()
                .content(modelMapper.map(saved, CategoryDto.class))
                .build();
    }

    @Override
    public ApiResponse<String> deleteCategory(Long categoryId) throws ResponseStatusException {

        if (!categoryRepository.existsById(categoryId)) {
            // throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not
            // found");
            throw new ResourceNotFoundException("Category", "categoryId", categoryId);
        }

        categoryRepository.deleteById(categoryId);
        return ApiResponse.<String>builder()
                .content("Category with id " + categoryId + " deleted successfully")
                .build();


    }

    @Override
    public ApiResponse<CategoryDto> updateCategory(Long categoryId, CategoryDto categoryDto) throws ResponseStatusException {
        Category categoryToUpdate = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        if (categoryDto.getCategoryName() != null) {
            categoryToUpdate.setCategoryName(categoryDto.getCategoryName());
        }

        Category saved = categoryRepository.save(categoryToUpdate);

        return ApiResponse.<CategoryDto>builder()
                .content(modelMapper.map(saved, CategoryDto.class))
                .build();
    }
}
