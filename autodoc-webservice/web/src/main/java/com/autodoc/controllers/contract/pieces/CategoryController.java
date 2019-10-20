package com.autodoc.controllers.contract.pieces;

import org.springframework.http.ResponseEntity;

public interface CategoryController {


    ResponseEntity getByName(String name);

}
