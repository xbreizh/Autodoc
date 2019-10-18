package com.autodoc.controllers.contract;

import org.springframework.http.ResponseEntity;

public interface GlobalController<T> {

    ResponseEntity getAll();

    ResponseEntity getById(Integer id);

    ResponseEntity delete(Integer id);
}
