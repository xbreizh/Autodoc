package com.autodoc.controllers.impl;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.controllers.contract.GlobalController;
import com.autodoc.controllers.helper.GsonConverter;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
/*@Controller
@RequestMapping("/*")*/
public abstract class GlobalControllerImpl<T> implements GlobalController {

    private Logger logger = Logger.getLogger(GlobalControllerImpl.class);
    //private Class<Object> clazz;

    private IGenericManager<T> manager;
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
        //List<Object> list = (List<Object>) manager.getAll();
        //logger.info("list: " + list);
        String response = converter.convertObjectIntoGsonObject(manager.getAll());

        return ResponseEntity.ok(response);
    }


   /* @GetMapping(value = "/getAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getAll() {
        logger.info("trying to get list of cars");
        List<Object> list = (List<Object>) globalManager.getAll();
        logger.info("list: " + list);
        String response = converter.convertObjectIntoGsonObject(list);

        return ResponseEntity.ok(response);
    }*/

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
    public ResponseEntity delete(@PathVariable Integer id) {
        logger.info("trying to delete: "+id);
        String response = manager.deleteById(id);
        if (response.equals(response)) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}
