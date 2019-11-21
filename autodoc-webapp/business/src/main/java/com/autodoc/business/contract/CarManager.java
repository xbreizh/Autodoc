package com.autodoc.business.contract;


import com.autodoc.model.models.car.Car;

public interface CarManager extends GlobalManager{




   // Car getById(String token, int id);

    Car getByRegistration(String connectedToken, String registration);




}
