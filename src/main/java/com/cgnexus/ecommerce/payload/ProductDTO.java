package com.cgnexus.ecommerce.payload;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long productId;

    @Size(min = 3, max = 100, message = "Product name must be between 5 and 100 characters")
    private String productName;

    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    private String description;
    private String image;

    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private Double price;

    @DecimalMin(value = "0.0", inclusive = true, message = "Special price must be greater than or equal to 0")
    private Double specialPrice;

    @DecimalMin(value = "0.0", message = "Discount cannot be negative")
    @DecimalMax(value = "100.0", message = "Discount cannot be more than 100%")
    private Double discount;
}
