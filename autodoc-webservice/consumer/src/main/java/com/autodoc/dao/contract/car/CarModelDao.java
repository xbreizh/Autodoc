package com.autodoc.dao.contract.car;

import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.model.models.car.CarModel;

public interface CarModelDao extends IGenericDao {


    CarModel findByName(String name);
}
