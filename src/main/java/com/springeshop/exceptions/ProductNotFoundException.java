package com.springeshop.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String e) {
        super(e);
    }
}
