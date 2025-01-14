package com.cgnexus.ecommerce.service;

import com.cgnexus.ecommerce.payload.ApiResponse;
import com.cgnexus.ecommerce.payload.ProductDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface ProductService {
    ProductDTO addProduct(ProductDTO productDTO, Long categoryId);

    ApiResponse<List<ProductDTO>> getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);


    ApiResponse<List<ProductDTO>> getAllProductsByCategory(Integer pageNumber, Integer pageSize, String sortDirection, String sortBy, Long categoryId);

    ApiResponse<List<ProductDTO>> getAllProductsByKeyWord(Integer pageNumber, Integer pageSize, String sortDirection, String sortBy, String keyword);

    ProductDTO updateProduct(ProductDTO productDTO, Long productId);

    void deleteProduct(Long productId);

    ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException;
}
