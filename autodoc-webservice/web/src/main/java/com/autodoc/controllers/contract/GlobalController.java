package com.autodoc.controllers.contract;

import org.springframework.http.ResponseEntity;

public interface GlobalController<T> {

    ResponseEntity getAll();

    ResponseEntity getById(Integer id);

    ResponseEntity add(final T entity);

    ResponseEntity update(final T entity);

    ResponseEntity delete(Integer id);
}
