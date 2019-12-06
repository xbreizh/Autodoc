package com.autodoc.controllers.contract;

import org.springframework.http.ResponseEntity;

public interface GlobalController<T, D> {

    ResponseEntity getAll();

    ResponseEntity getById(Integer id) throws Exception;

    ResponseEntity getByName(String name) throws Exception;

    ResponseEntity deleteById(Integer id) throws Exception;



}
