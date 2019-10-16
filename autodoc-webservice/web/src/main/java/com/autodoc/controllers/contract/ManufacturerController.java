package com.autodoc.controllers.contract;

import com.autodoc.model.models.car.Manufacturer;

import java.util.List;

public interface ManufacturerController {

    List<Manufacturer> getAll();

    Manufacturer getByName(String name);

}
