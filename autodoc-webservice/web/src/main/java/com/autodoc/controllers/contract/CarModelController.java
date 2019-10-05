package com.autodoc.controllers.contract;

import com.autodoc.model.models.car.CarModel;

public interface CarModelController {


    String getAll();

    String getCarModelsByManufacturer(String manufacturer);

    String getById(int id);


}
