package com.exception;

public class PostalCodeNotFoundException extends RuntimeException {

    public PostalCodeNotFoundException(String message) {
        super(message);
    }
}
