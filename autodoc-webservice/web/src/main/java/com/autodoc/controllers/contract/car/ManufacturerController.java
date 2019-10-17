package com.autodoc.controllers.contract.car;

import org.springframework.http.ResponseEntity;

public interface ManufacturerController {

    ResponseEntity getAll();

    ResponseEntity getByName(String name);

}
