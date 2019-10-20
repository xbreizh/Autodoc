package com.autodoc.controllers.contract;

import org.springframework.http.ResponseEntity;

public interface GlobalController<D, T> {

    ResponseEntity getAll();

    ResponseEntity getById(Integer id);

    ResponseEntity deleteById(Integer id);



}
