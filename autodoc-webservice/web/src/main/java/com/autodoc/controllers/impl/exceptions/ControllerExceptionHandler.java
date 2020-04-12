/*
package com.autodoc.controllers.impl.exceptions;

import com.autodoc.business.exceptions.InvalidDtoException;
import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.NestedServletException;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class ControllerExceptionHandler extends  RuntimeException {

    private static final Logger LOGGER = Logger.getLogger(ControllerExceptionHandler.class);

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class, SQLException.class, NestedServletException.class})
    public ResponseEntity notFoundHandler(Exception e, HttpServletRequest req) {

        if (e.getClass() == ObjectNotFoundException.class) return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("No object found");
        LOGGER.error("capturing" + e.getClass() + e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());

    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity entityValidation(InvalidDtoException e) {
        LOGGER.info("handling entityValidation exception: " + e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }




    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InvalidDtoException.class)
    public ResponseEntity invalidDto(InvalidDtoException e) {
        LOGGER.info("handling dto exception: " + e.getMessage());
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
        LOGGER.info("handling 400: " + e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }


    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity handle401Unauthorized(AuthenticationException e) {
        LOGGER.info("handling 401: " + e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationExceptions(MethodArgumentNotValidException ex) {
        LOGGER.info(ex.getMessage());
        LOGGER.info(ex.getBindingResult());
        LOGGER.debug("bad request ?");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getBindingResult()
                        .getAllErrors().stream()
                        .map(ObjectError::getDefaultMessage)
                        .collect(Collectors.toList()).toString());
    }


}
*/
