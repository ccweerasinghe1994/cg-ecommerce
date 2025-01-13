package com.cgnexus.ecommerce.repositories;

import com.cgnexus.ecommerce.model.Category;
import com.cgnexus.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCategory(Category category, Pageable pageable);

    Page<Product> findByProductNameContainingIgnoreCase(String productName, Pageable pageable);
}
