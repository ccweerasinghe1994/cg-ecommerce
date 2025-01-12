package com.cgnexus.ecommerce.repositories;

import com.cgnexus.ecommerce.model.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByCategoryName(
            @NotBlank(message = "Category name is required")
            @Min(value = 5, message = "Category name should have at least 5 characters")
            String categoryName);
}
