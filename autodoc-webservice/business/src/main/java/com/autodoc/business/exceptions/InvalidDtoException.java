package com.autodoc.business.exceptions;

public class InvalidDtoException extends RuntimeException {

    public InvalidDtoException(String errorMessage) {
        super(errorMessage);
    }
}
