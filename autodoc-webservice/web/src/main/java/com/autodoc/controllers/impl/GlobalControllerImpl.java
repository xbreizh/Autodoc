package com.autodoc.controllers.impl;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.controllers.contract.GlobalController;
import com.autodoc.controllers.helper.GsonConverter;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public abstract class GlobalControllerImpl<T, D> implements GlobalController {

    String type = "";
    private Logger logger = Logger.getLogger(GlobalControllerImpl.class);
    private IGenericManager<T, D> manager;
    private GsonConverter converter;

    public GlobalControllerImpl(IGenericManager manager) {
        converter = new GsonConverter();
        this.manager = manager;
    }

    @Override
    @GetMapping(value = "/getAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getAll() {
        logger.info("trying to get list of ");
        String response = converter.convertObjectIntoGsonObject(manager.getAll());

        return ResponseEntity.ok(response);
    }

    //@Override
    //@Override
    @PostMapping(value = "/add",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity add(@RequestBody D obj) {
        getClassName(obj);
        logger.debug("trying to add a " + type);
        String response = manager.save(obj);
        if (response.equals(type + " added")) {
            return ResponseEntity.ok(type + " added");
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);

    }

    //@Override
    // @Override
    @PutMapping(value = "/update",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody D obj) {
        getClassName(obj);
        logger.debug("trying to update a " + obj);
        String response = manager.update(obj);
        if (response.equals(obj + " updated")) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }


    @Override
    @GetMapping(value = "/getById/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getById(@PathVariable Integer id) {
        String response = converter.convertObjectIntoGsonObject(manager.getById(id));

        return ResponseEntity.ok(response);
    }


    @Override
    @DeleteMapping(value = "/deleteById/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteById(@PathVariable Integer id) {
        logger.info("trying to delete: " + id);
        String response = manager.deleteById(id);
        if (response.equals(response)) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    private void getClassName(D obj) {
        type = obj.getClass().getSimpleName();
    }
}
