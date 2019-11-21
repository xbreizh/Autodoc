package com.autodoc.contract;

import com.autodoc.model.dtos.car.CarDTO;


public interface CarService extends GlobalService {
    CarDTO getByRegistration(String token, String registration);
}
