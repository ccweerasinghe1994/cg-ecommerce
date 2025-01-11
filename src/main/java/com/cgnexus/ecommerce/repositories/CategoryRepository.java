package com.cgnexus.ecommerce.repositories;

import com.cgnexus.ecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
