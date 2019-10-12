package com.autodoc.dao.contract.car;

import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.model.models.car.Manufacturer;

public interface ManufacturerDao extends IGenericDao {


    Manufacturer getByName(String name);
}
