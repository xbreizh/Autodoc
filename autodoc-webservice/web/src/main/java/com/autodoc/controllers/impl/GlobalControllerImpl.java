package com.autodoc.controllers.impl;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.controllers.contract.GlobalController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.model.models.search.SearchDTO;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


public abstract class GlobalControllerImpl<T, D> implements GlobalController {

    protected HttpHeaders responseHeaders;
    String type = "";
    private static final Logger LOGGER = Logger.getLogger(GlobalControllerImpl.class);
    private IGenericManager<T, D> manager;
    private GsonConverter converter;

    protected ResponseEntity notFoundResponse = ResponseEntity
            .status(HttpStatus.NOT_FOUND).body("");

    public GlobalControllerImpl(IGenericManager manager) {
       /* responseHeaders=new HttpHeaders();
        responseHeaders.set("Autodoc-Header",
                "autodoc-header-value");*/
        converter = new GsonConverter();
        this.manager = manager;
    }

    @Override
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getAll() {
        LOGGER.info("trying to get list of ");
        String response = converter.convertObjectIntoGsonObject(manager.getAll());
        LOGGER.info("response " + response);

        ResponseEntity entity = ResponseEntity.ok()
                .headers(responseHeaders)
                .body(response);
        return entity;
        // return ResponseEntity.ok(response);
    }

    //@Override
    @PostMapping(value = "",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity add(@RequestBody @Valid D obj) throws Exception {
        getClassName(obj);
        LOGGER.info("trying to add a " + type);
        LOGGER.info("object received: " + obj);
        String response = manager.save(obj);
        LOGGER.info("response: " + response);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(Integer.parseInt(response));
        } catch (InvalidDtoException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }


    }


   // @Override
    @PostMapping(value = "/criteria",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity searchByCriteria(@RequestBody @Valid List<SearchDTO> searchDTO)throws Exception{

        LOGGER.info("getting by criteria: " + searchDTO.get(0));
       /* for (SearchDTO s: searchDTOs) {
            LOGGER.info("searching by criteria: " + s.getFieldName() + " " + s.getCompare() + " " + s.getValue());
        }
       List<SearchDTO> searchDTOS = new ArrayList<>();
       searchDTOS.add(searchDTO);*/
        String response = converter.convertObjectIntoGsonObject(manager.searchByCriteria(searchDTO));
        LOGGER.info(response);
        return ResponseEntity.ok(response);
       // return ResponseEntity.ok("glop");
    }

    //@Override
    // @Override
    @PutMapping(
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody D obj) throws Exception {
        getClassName(obj);
        LOGGER.debug("trying to update a " + obj);
        LOGGER.info("trying to update: " + obj);
        boolean response = manager.update(obj);
        if (response) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }


    @Override
    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getById(@PathVariable Integer id) throws Exception {
        Object received = manager.getById(id);
        if (received == null) return notFoundResponse;
        LOGGER.info("reaced ");
        String response = converter.convertObjectIntoGsonObject(received);
        LOGGER.info("returned: " + manager.getById(id));
        ResponseEntity entity = ResponseEntity.ok(response);
        LOGGER.info("entity: " + entity);
        return entity;
    }

    @Override
    @GetMapping(value = "/name",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getByName(@RequestParam(value = "name") String name) throws Exception {
        LOGGER.debug("trying to get: " + name);
        Object received = manager.getByName(name);
        if (received == null) return notFoundResponse;
        LOGGER.info("trying to get by name: " + name);
        String response = converter.convertObjectIntoGsonObject(received);
        if (response == null) return notFoundResponse;
        return ResponseEntity.ok(response);
    }


    @Override
    @DeleteMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteById(@PathVariable Integer id) throws Exception {
        LOGGER.info("trying to delete");
        LOGGER.info("trying to delete: " + id);
        boolean response = manager.deleteById(id);
        if (!response) return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("invalid id: " + id);
        if (response) {
            return ResponseEntity.status(204).body(response);
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }


    private void getClassName(D obj) {
        type = obj.getClass().getSimpleName();
    }
}

