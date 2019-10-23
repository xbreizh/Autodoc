package com.autodoc.controllers.impl.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class ControllerExceptionHandler extends RuntimeException{

    private static Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class, SQLException.class, ConstraintViolationException.class})
    public ResponseEntity notFoundHandler(Exception e, HttpServletRequest req) {
        logger.info("Item not found. HTTP 500 returned.");
        System.out.println("req: "+req.getRequestURI());
        logger.info("capturing 500 " + e.getMessage());
        System.out.println("blop1: " + e.getLocalizedMessage());
        System.out.println("exception raised: " + e.getClass());
        System.out.println("cause: " + e.getCause());
        System.out.println("ww: " + e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle404(HttpServletRequest req) {
        System.out.println("req: "+req.getRequestURI());
        return "exceptions/404page";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handle400(EntityNotFoundException e) {
        System.out.println("handling 400: "+e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }


/*
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        System.out.println("hara kirikou");
        *//*return ex.getBindingResult()
                .getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());*//*
        System.out.println("ww: "+ex.getLocalizedMessage());
        System.out.println("ewe: "+ex.getMessage());
        System.out.println("frfr: "+ex.toString());
        System.out.println("qq: "+ex.getClass());
        *//*return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());*//*
        return ex.getBindingResult()
                .getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
    }*/

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationExceptions(MethodArgumentNotValidException ex) {
       /* return ex.getBindingResult()
                .getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
        */
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getBindingResult()
                        .getAllErrors().stream()
                        .map(ObjectError::getDefaultMessage)
                        .collect(Collectors.toList()).toString());
    }


}