package com.autodoc.controllers.contract.car;

import com.autodoc.controllers.contract.GlobalController;
import org.springframework.http.ResponseEntity;

public interface ManufacturerController extends GlobalController {

    ResponseEntity getAll();

    ResponseEntity getByName(String name);

    ResponseEntity getById(Integer id);

}
