package com.autodoc.controllers.contract;

import com.autodoc.model.models.car.Manufacturer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ManufacturerController {

    ResponseEntity getAll();

    ResponseEntity getByName(String name);

}
