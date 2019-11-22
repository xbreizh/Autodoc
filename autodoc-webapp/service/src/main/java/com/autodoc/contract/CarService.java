package com.autodoc.contract;

import com.autodoc.model.dtos.CarDTO;


public interface CarService extends GlobalService {
    CarDTO getByRegistration(String token, String registration);
}
