package com.api.exception;

public class PostalCodeNotFoundException extends RuntimeException {

    public PostalCodeNotFoundException(String message) {
        super(message);
    }
}
