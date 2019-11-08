package com.autodoc.controllers.impl;

import com.autodoc.business.contract.IGenericManager;
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

        return ResponseEntity.ok()
                //.headers(responseHeaders)
                .body(response);
        // return ResponseEntity.ok(response);
    }

    //@Override
    @PostMapping(value = "",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity add(@RequestBody @Valid D obj) throws Exception {
        getClassName(obj);
        LOGGER.info("trying to add a " + type);
        String response = manager.save(obj);
        if (response.equals(type + " added")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(type + "created");
        }
        System.out.println("trok: " + response);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);

    }


   // @Override
    @PostMapping(value = "/criteria",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity searchByCriteria(@RequestBody @Valid List<SearchDTO> searchDTO)throws Exception{

        System.out.println("getting by criteria: "+searchDTO.get(0));
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
    @PutMapping(value = "/update",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody D obj) throws Exception {
        getClassName(obj);
        LOGGER.debug("trying to update a " + obj);
        D response = manager.update(obj);
        if (response.equals(obj + " updated")) {
            return ResponseEntity.ok(response);
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
        String response = converter.convertObjectIntoGsonObject(manager.getById(id));

        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping(value = "/name",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getByName(@RequestParam(value = "name") String name) throws Exception {
        LOGGER.debug("trying to get: " + name);
        // ManufacturerDTO manufacturer = manager.getByName(name);
        //LOGGER.debug("manufacturer: " + manufacturer);
        String response = converter.convertObjectIntoGsonObject(manager.getByName(name));

        return ResponseEntity.ok(response);
    }


    @Override
    @DeleteMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteById(@PathVariable Integer id) {
        LOGGER.info("trying to delete: " + id);
        String response = manager.deleteById(id);
        if (response.equals("notFound")) return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("invalid id: " + id);
        if (response.equals(response)) {
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
