package com.autodoc.business.contract.car;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.dtos.car.ManufacturerDTO;
import org.springframework.stereotype.Service;

@Service
public interface ManufacturerManager extends IGenericManager {


    // List<ManufacturerDTO> getAll();

    ManufacturerDTO getByName(String anyString);
}
