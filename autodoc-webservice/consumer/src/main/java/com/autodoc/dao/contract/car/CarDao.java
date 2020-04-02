package com.autodoc.dao.contract.car;

import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.model.models.car.Car;

import java.util.List;

public interface CarDao extends IGenericDao {

    Car getCarByRegistration(String registration);

    List<Car> getCarByClient(String lastName);

    //  List<Car> getByCriteria();
}
