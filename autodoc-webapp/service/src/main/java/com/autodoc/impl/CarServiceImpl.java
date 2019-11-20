package com.autodoc.impl;

import com.autodoc.contract.CarService;
import com.autodoc.model.Car;

import javax.inject.Named;

@Named
public class CarServiceImpl extends GlobalServiceImpl<Car> implements CarService {

    Class getObjectClass() {
        return Car.class;
    }

}
