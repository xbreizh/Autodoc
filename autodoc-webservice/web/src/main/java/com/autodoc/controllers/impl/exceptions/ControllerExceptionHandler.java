package com.autodoc.controllers.impl.exceptions;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.util.NestedServletException;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class ControllerExceptionHandler extends RuntimeException {

    private static final Logger LOGGER = Logger.getLogger(ControllerExceptionHandler.class);

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class, SQLException.class, ConstraintViolationException.class, NestedServletException.class})
    public ResponseEntity notFoundHandler(Exception e, HttpServletRequest req) {
        LOGGER.info("Item not found. HTTP 500 returned.");
        LOGGER.debug("req: " + req.getRequestURI());
        LOGGER.info("capturing 500 " + e.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity handle404(HttpServletRequest req) {
        LOGGER.debug("req: " + req.getRequestURI());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Invalid url: " + req.getRequestURI());
    }


    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler({NoResultException.class, EntityNotFoundException.class})
    public ResponseEntity handle400(EntityNotFoundException e) {
        System.out.println("handling 400: " + e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }


    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity handle401Unauthorized(AuthenticationException e) {
        System.out.println("handling 401: " + e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationExceptions(MethodArgumentNotValidException ex) {

        LOGGER.debug("bad request ?");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getBindingResult()
                        .getAllErrors().stream()
                        .map(ObjectError::getDefaultMessage)
                        .collect(Collectors.toList()).toString());
    }


}