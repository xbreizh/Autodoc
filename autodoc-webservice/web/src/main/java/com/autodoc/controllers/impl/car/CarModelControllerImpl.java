package com.autodoc.controllers.impl.car;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.business.contract.car.CarModelManager;
import com.autodoc.controllers.contract.car.CarModelController;
import com.autodoc.controllers.impl.GlobalControllerImpl;
import com.autodoc.model.dtos.car.CarModelDTO;
import com.autodoc.model.models.car.CarModel;
import lombok.Builder;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/carModels")
@Builder
public class CarModelControllerImpl extends GlobalControllerImpl<CarModel, CarModelDTO> implements CarModelController {

    private static final Logger LOGGER = Logger.getLogger(CarModelControllerImpl.class);
    private CarModelManager carModelManager;


    public IGenericManager getManager() {
        return carModelManager;
    }


    @Override
    public ResponseEntity getCarModelsByManufacturer(String manufacturer) {
        return null;
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
