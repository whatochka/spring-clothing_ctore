package com.springeshop.exceptions;

public class PasswordNotEqualsException extends RuntimeException {
    public PasswordNotEqualsException(String message) {
        super(message);
    }
}
