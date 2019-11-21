package com.autodoc.contract;

import com.autodoc.model.CarDTO;


public interface CarService extends GlobalService {
    CarDTO getByRegistration(String token, String registration);
}
