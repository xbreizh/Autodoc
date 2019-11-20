package com.autodoc.contract;

import com.autodoc.model.Car;

import javax.inject.Named;


public interface CarService extends GlobalService {
    Car getByRegistration(String token, String registration);
}
