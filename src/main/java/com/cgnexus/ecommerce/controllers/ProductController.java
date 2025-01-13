package com.cgnexus.ecommerce.controllers;

import com.cgnexus.ecommerce.payload.ApiResponse;
import com.cgnexus.ecommerce.payload.ProductDTO;
import com.cgnexus.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cgnexus.ecommerce.config.AppConstants.*;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/admin/categories/{categoryId}/product")
    public ResponseEntity<ApiResponse<ProductDTO>> addProduct(
            @RequestBody ProductDTO productDTO,
            @PathVariable(name = "categoryId") Long categoryId) {
        ProductDTO response = productService.addPost(productDTO, categoryId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.<ProductDTO>builder().content(response).build());
    }

    @GetMapping("/public/products")
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getAllProducts(
            @RequestParam(name = "pageNumber", defaultValue = DEFAULT_PAGE_NUMBER) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = "productId") String sortBy,
            @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection
    ) {
        ApiResponse<List<ProductDTO>> response = productService.getAllProducts(pageNumber, pageSize, sortBy, sortDirection);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/public/categories/{categoryId}/product")
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getProductsByCategory(
            @RequestParam(name = "pageNumber", defaultValue = DEFAULT_PAGE_NUMBER) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize,
            @RequestParam(name = "sortDirection", defaultValue = DEFAULT_SORT_DIRECTION) String sortDirection,
            @RequestParam(name = "sortBy", defaultValue = DEFAULT_PRODUCT_SORT_BY) String sortBy,
            @PathVariable(name = "categoryId") Long categoryId

    ) {
        ApiResponse<List<ProductDTO>> response = productService.getAllProductsByCategory(pageNumber, pageSize, sortDirection, sortBy, categoryId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/public/products/keyword/{keyword}")
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getAllProductsByKeyWord(
            @RequestParam(name = "pageNumber", defaultValue = DEFAULT_PAGE_NUMBER) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize,
            @RequestParam(name = "sortDirection", defaultValue = DEFAULT_SORT_DIRECTION) String sortDirection,
            @RequestParam(name = "sortBy", defaultValue = DEFAULT_PRODUCT_SORT_BY) String sortBy,
            @PathVariable(name = "keyword") String keyword
    ) {
        ApiResponse<List<ProductDTO>> response = productService.getAllProductsByKeyWord(pageNumber, pageSize, sortDirection, sortBy, keyword);
        return ResponseEntity.ok(response);
    }
}
