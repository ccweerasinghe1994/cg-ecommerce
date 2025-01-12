package com.cgnexus.ecommerce.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {
    HttpStatus status;

    public ApiException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
