package com.autodoc.controllers.impl;


import com.autodoc.business.contract.car.ManufacturerManager;
import com.autodoc.business.contract.person.employee.EmployeeManager;
import com.autodoc.business.impl.authentication.JwtConnect;
import com.autodoc.controllers.contract.ManufacturerController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.car.Manufacturer;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manufacturer")
public class ManufacturerControllerImpl implements ManufacturerController {
    private Logger logger = Logger.getLogger(ManufacturerControllerImpl.class);
    private ManufacturerManager manufacturerManager;
    private GsonConverter converter;
    private EmployeeManager employeeManager;
    private JwtConnect connect;

    public ManufacturerControllerImpl(ManufacturerManager manufacturerManager, JwtConnect connect) {
        if (converter == null) converter = new GsonConverter();
        this.manufacturerManager = manufacturerManager;
        this.connect = connect;
    }



    @GetMapping(value = "/getAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getAll() {

        List<Manufacturer> list = manufacturerManager.getAll();
        logger.debug("list: "+list);
        String response = converter.convertObjectIntoGsonObject(list);

        return response;
    }



}
