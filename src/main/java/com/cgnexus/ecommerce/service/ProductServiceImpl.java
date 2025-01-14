package com.cgnexus.ecommerce.service;

import com.cgnexus.ecommerce.exception.ResourceNotFoundException;
import com.cgnexus.ecommerce.model.Category;
import com.cgnexus.ecommerce.model.Product;
import com.cgnexus.ecommerce.payload.ApiResponse;
import com.cgnexus.ecommerce.payload.ProductDTO;
import com.cgnexus.ecommerce.repositories.CategoryRepository;
import com.cgnexus.ecommerce.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @Override
    public ProductDTO addPost(ProductDTO productDTO, Long categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("category", "categoryId", categoryId)
        );

        Product product = mapper.map(productDTO, Product.class);

        product.setCategory(category);

        double discountedPrice = product.getPrice() - (product.getDiscount() * 0.01) * product.getPrice();
        product.setSpecialPrice(discountedPrice);

        Product save = productRepository.save(product);

        return mapper.map(save, ProductDTO.class);
    }

    @Override
    public ApiResponse<List<ProductDTO>> getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> products = productRepository.findAll(pageable);
        return getListApiResponse(products);

    }

    @Override
    public ApiResponse<List<ProductDTO>> getAllProductsByCategory(
            Integer pageNumber,
            Integer pageSize,
            String sortDirection,
            String sortBy,
            Long categoryId
    ) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));

        Page<Product> byCategoryProductsPage = productRepository.findByCategory(category, pageable);
        return getListApiResponse(byCategoryProductsPage);
    }

    @Override
    public ApiResponse<List<ProductDTO>> getAllProductsByKeyWord(Integer pageNumber, Integer pageSize, String sortDirection, String sortBy, String keyword) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Product> byProductNameIsLikeIgnoreCase = productRepository.findByProductNameContainingIgnoreCase(keyword, pageable);
        return getListApiResponse(byProductNameIsLikeIgnoreCase);
    }

    @Override
    @Transactional
    public ProductDTO updateProduct(ProductDTO productDTO, Long productId) {

        Product existingProduct = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        validateProductInput(productDTO);

        // Calculate special price
        if (productDTO.getPrice() != null || productDTO.getDiscount() != null) {
            double newPrice = productDTO.getPrice() != null ? productDTO.getPrice() : existingProduct.getPrice();
            double newDiscount = productDTO.getDiscount() != null ? productDTO.getDiscount() : existingProduct.getDiscount();
            double specialPrice = calculateSpecialPrice(newPrice, newDiscount);
            existingProduct.setSpecialPrice(specialPrice);
        }

        if (productDTO.getProductName() != null && !productDTO.getProductName().isBlank()) {
            existingProduct.setProductName(productDTO.getProductName());
        }
        if (productDTO.getDescription() != null && !productDTO.getDescription().isBlank()) {
            existingProduct.setDescription(productDTO.getDescription());
        }

        if (productDTO.getQuantity() != null) existingProduct.setQuantity(productDTO.getQuantity());
        if (productDTO.getPrice() != null) existingProduct.setPrice(productDTO.getPrice());
        if (productDTO.getDiscount() != null) existingProduct.setDiscount(productDTO.getDiscount());


        Product save = productRepository.save(existingProduct);

        return mapper.map(save, ProductDTO.class);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));
        productRepository.delete(product);
    }

    private ApiResponse<List<ProductDTO>> getListApiResponse(Page<Product> byCategoryProductsPage) {
        List<Product> productList = byCategoryProductsPage.getContent();
        List<ProductDTO> productDTOList = productList.stream().map(product -> mapper.map(product, ProductDTO.class)).toList();
        return ApiResponse.<List<ProductDTO>>builder()
                .content(productDTOList)
                .lastPage(byCategoryProductsPage.isLast())
                .totalPages(byCategoryProductsPage.getTotalPages())
                .pageNumber(byCategoryProductsPage.getNumber())
                .totalElements(byCategoryProductsPage.getTotalElements())
                .pageSize(byCategoryProductsPage.getSize())
                .build();
    }

    private void validateProductInput(ProductDTO productDTO) {
        if (productDTO.getPrice() != null && productDTO.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        if (productDTO.getDiscount() != null && (productDTO.getDiscount() < 0 || productDTO.getDiscount() > 100)) {
            throw new IllegalArgumentException("Discount must be between 0 and 100");
        }
        if (productDTO.getQuantity() != null && productDTO.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
    }

    private double calculateSpecialPrice(double price, double discount) {
        double discountAmount = price * (discount / 100.0);
        return Math.round((price - discountAmount) * 100.0) / 100.0;
    }
}
