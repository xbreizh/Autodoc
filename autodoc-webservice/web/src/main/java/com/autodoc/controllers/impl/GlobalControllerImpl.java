package com.autodoc.controllers.impl;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.controllers.contract.GlobalController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.model.models.search.Search;
import com.autodoc.model.models.search.SearchDTO;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
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
    protected final GsonConverter converter = new GsonConverter();

    protected ResponseEntity<String> notFoundResponse = ResponseEntity
            .status(HttpStatus.NOT_FOUND).body("");


    public IGenericManager<T, D> getManager() {
        return null;
    }


    @GetMapping(value = "/")
    public String home() {
        return "index";
    }


    @GetMapping(value = "/admin")
    public String privateHome() {
        return "privatePage";
    }

    @Override
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getAll() {
        String response = converter.convertObjectIntoGsonObject(getManager().getAll());
        LOGGER.info("response " + response);

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(response);
    }

    @PostMapping(value = "",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> add(@RequestBody @Valid D obj)  {
        try {
            System.out.println("trying to add: " + obj);
            IGenericManager<T, D> manager = getManager();
            getClassName(obj);
            LOGGER.info("trying to add a " + type);
            LOGGER.info("object received: " + obj);
            String response = manager.save(obj);
            LOGGER.info("response: " + response);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        catch (ConstraintViolationException exception){
            LOGGER.error("error dude");
            return ResponseEntity.status(HttpStatus.CREATED).body("paf error");
        }

    }

    @PostMapping(value = "/criteria",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> searchByCriteria(@RequestBody @Valid List<SearchDTO> searchDTO)  {
        IGenericManager<T,D> manager = getManager();

        LOGGER.info("getting by criteria: " + searchDTO.get(0));
        String response = converter.convertObjectIntoGsonObject(manager.searchByCriteria(searchDTO));
        LOGGER.info(response);
        return ResponseEntity.ok(response);
    }

    @PutMapping(
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> update(@RequestBody D obj)  {
        IGenericManager<T,D> manager = getManager();
        getClassName(obj);
        LOGGER.info("trying to update: " + obj);
        boolean response = manager.update(obj);
        if (response) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(false);
    }


    @Override
    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getById(@PathVariable Integer id)  {
        IGenericManager<T,D> manager = getManager();
        Object received = manager.getById(id);
        if (received == null) return notFoundResponse;
        LOGGER.info("reaced ");
        String response = converter.convertObjectIntoGsonObject(received);
        LOGGER.info("returned: " + manager.getById(id));
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping(value = "/name",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getByName(@RequestParam(value = "name") String name) throws Exception {
        IGenericManager<T,D> manager = getManager();
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
    public ResponseEntity deleteById(@PathVariable Integer id)  {
        IGenericManager<T,D> manager = getManager();
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

