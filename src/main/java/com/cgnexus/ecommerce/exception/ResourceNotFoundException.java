package com.cgnexus.ecommerce.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String field;
    String fieldName;
    Long filedId;

    public ResourceNotFoundException(String resourceName, String field, String fieldName) {
        super(String.format("%s not found with %s : %s", resourceName, field, fieldName));
        this.field = field;
        this.fieldName = fieldName;
        this.resourceName = resourceName;
    }

    public ResourceNotFoundException(String resourceName, String field, Long filedId) {
        super(String.format("%s not found with %s : %d", resourceName, field, filedId));
        this.field = field;
        this.filedId = filedId;
        this.resourceName = resourceName;
    }
}
