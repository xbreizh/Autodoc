package com.autodoc.business.contract.car;

import com.autodoc.model.models.car.Manufacturer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ManufacturerManager {


    List<Manufacturer> getAll();

}
