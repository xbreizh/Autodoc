package com.autodoc.controllers.contract;

import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.person.client.Client;

public interface CarModelController {


    String getAll();

    Car getCarModelsByManufacturer(String manufacturer);


}
