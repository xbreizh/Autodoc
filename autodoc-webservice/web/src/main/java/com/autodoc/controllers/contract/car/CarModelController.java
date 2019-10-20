package com.autodoc.controllers.contract.car;

import org.springframework.http.ResponseEntity;

public interface CarModelController {


    ResponseEntity getCarModelsByManufacturer(String manufacturer);


}
