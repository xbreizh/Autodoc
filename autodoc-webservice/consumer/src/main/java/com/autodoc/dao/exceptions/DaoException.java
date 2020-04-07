package com.autodoc.dao.exceptions;

public class DaoException extends RuntimeException {

    public DaoException(String errorMessage) {
        super(errorMessage);
    }
}
