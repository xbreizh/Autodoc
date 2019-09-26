package com.autodoc.dao.contract;

import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.model.models.car.Car;

public interface CarDao extends IGenericDao {

    Car getCarByRegistration(String registration);
}
