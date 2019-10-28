package com.autodoc.controllers.impl.car;

import com.autodoc.business.contract.car.CarModelManager;
import com.autodoc.controllers.contract.car.CarModelController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.controllers.impl.GlobalControllerImpl;
import com.autodoc.model.dtos.car.CarModelDTO;
import com.autodoc.model.models.car.CarModel;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carModels")
public class CarModelControllerImpl extends GlobalControllerImpl<CarModel, CarModelDTO> implements CarModelController {

    private static final Logger LOGGER = Logger.getLogger(CarModelControllerImpl.class);
    private CarModelManager carModelManager;
    private GsonConverter converter;

    public CarModelControllerImpl(CarModelManager carModelManager) {
        super(carModelManager);
        converter = new GsonConverter();
        this.carModelManager = carModelManager;
    }

  /*  @GetMapping(value = "/getAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getAll() {

        List<CarModel> list = carModelManager.getAll();
        LOGGER.debug("Loaded |" + list + "|");
        String response = converter.convertObjectIntoGsonObject(list);
        LOGGER.debug("Returning |" + response + "|");


        return ResponseEntity.ok(response);
    }*/

    @Override
    public ResponseEntity getCarModelsByManufacturer(String manufacturer) {
        return null;
    }

  /*  @GetMapping(value = "/getById/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getById(@PathVariable int id) {
        CarModel carModel = carModelManager.getById(id);
        //return converter.convertObjectIntoGsonObject(carModel);
        String response = converter.convertObjectIntoGsonObject(carModel);

        return ResponseEntity.ok(response);
    }*/

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


    @GetMapping(value = "/getByName",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getByName(@RequestBody String name) {
        LOGGER.debug("getting: " + name);
        CarModelDTO carModel = carModelManager.getByName(name);
        String response = converter.convertObjectIntoGsonObject(carModel);
        return ResponseEntity.ok(response);
    }
/*
    @Override
    @GetMapping(value = "/getByName",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getCarByRegistration(@RequestBody String registration) {
        Car car = carManager.getByRegistration(registration);
        String response = converter.convertObjectIntoGsonObject(car);

        return ResponseEntity.ok(response);
    }*/
}
