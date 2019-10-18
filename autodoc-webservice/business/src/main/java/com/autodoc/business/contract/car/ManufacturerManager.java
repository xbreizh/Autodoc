package com.autodoc.business.contract.car;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.models.car.Manufacturer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ManufacturerManager extends IGenericManager {


    List<Manufacturer> getAll();

    Manufacturer getByName(String anyString);
}
