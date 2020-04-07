package com.autodoc.dao.exceptions;

public class InvalidDtoException extends RuntimeException {

    public InvalidDtoException(String errorMessage) {
        super(errorMessage);
    }
}
