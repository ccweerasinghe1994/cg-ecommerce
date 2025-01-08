package com.cgnexus.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {

    private Long categoryId;
    private String categoryName;
}
