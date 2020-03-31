package com.autodoc.dao.contract.car;

import com.autodoc.dao.contract.global.IGenericDao;

import java.util.List;

public interface CarDao<Car> extends IGenericDao {

    Car getCarByRegistration(String registration);

    List<Car> getCarByClient(String lastName);

    //  List<Car> getByCriteria();
}
