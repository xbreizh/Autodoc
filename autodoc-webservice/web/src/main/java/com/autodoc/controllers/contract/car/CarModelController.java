package com.autodoc.controllers.contract.car;

import org.springframework.http.ResponseEntity;

public interface CarModelController {


    //ResponseEntity getAll();

    ResponseEntity getCarModelsByManufacturer(String manufacturer);

    //ResponseEntity getById(Integer id);


}
