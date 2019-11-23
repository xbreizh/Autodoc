package com.autodoc.controllers.contract.car;

import com.autodoc.controllers.contract.GlobalController;
import org.springframework.http.ResponseEntity;

public interface CarModelController extends GlobalController {


    ResponseEntity getCarModelsByManufacturer(String manufacturer);


}
