package com.autodoc.controllers.contract;

public interface CarModelController {


    String getAll();

    String getCarModelsByManufacturer(String manufacturer);

    String getById(int id);


}
