package com.autodoc.controllers.contract;

import com.autodoc.model.models.car.Car;

public interface CarModelController {


    String getAll();

    Car getCarModelsByManufacturer(String manufacturer);


}
