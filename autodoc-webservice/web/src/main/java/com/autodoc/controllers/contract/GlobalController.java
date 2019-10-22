package com.autodoc.controllers.contract;

import org.springframework.http.ResponseEntity;

public interface GlobalController<T, D> {

    ResponseEntity getAll();

    ResponseEntity getById(Integer id);

    ResponseEntity deleteById(Integer id);


}
