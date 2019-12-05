package com.autodoc.controllers.impl.car;


import com.autodoc.business.contract.car.ManufacturerManager;
import com.autodoc.controllers.contract.car.ManufacturerController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.controllers.impl.GlobalControllerImpl;
import com.autodoc.model.dtos.car.ManufacturerDTO;
import com.autodoc.model.models.car.Manufacturer;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manufacturers")
public class ManufacturerControllerImpl extends GlobalControllerImpl<Manufacturer, ManufacturerDTO> implements ManufacturerController {
    private static final Logger LOGGER = Logger.getLogger(ManufacturerControllerImpl.class);
    private ManufacturerManager manufacturerManager;
    private GsonConverter converter;

    public ManufacturerControllerImpl(ManufacturerManager manufacturerManager) {
        super(manufacturerManager);
        converter = new GsonConverter();
        this.manufacturerManager = manufacturerManager;
    }


    @Override
    @DeleteMapping(value = "/deleteById/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteById(@PathVariable Integer id) {
        LOGGER.info("trying to delete: " + id);
        String response = "impossible action";
        if (response.equals(response)) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(response);
    }

}
