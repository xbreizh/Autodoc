/*
package com.autodoc.controllers.impl.person.provider;


import com.autodoc.business.contract.IGenericManager;
import com.autodoc.business.contract.person.provider.CountryManager;
import com.autodoc.controllers.contract.person.provider.CountryController;
import com.autodoc.controllers.impl.GlobalControllerImpl;
import com.autodoc.model.dtos.person.provider.CountryDTO;
import com.autodoc.model.models.person.provider.Country;
import lombok.Builder;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/countries")
@Builder
public class CountryControllerImpl extends GlobalControllerImpl<Country, CountryDTO> implements CountryController {
    private final static Logger LOGGER = Logger.getLogger(CountryControllerImpl.class);
    private CountryManager manager;


    public IGenericManager getManager() {
        return manager;
    }


    @PostMapping(value = "",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity add(@RequestBody CountryDTO obj) throws Exception {
        LOGGER.error("trying to use a not yet implemented method");
        throw new Exception(HttpStatus.NOT_IMPLEMENTED.getReasonPhrase());
    }

    @PutMapping(
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody CountryDTO obj) throws Exception {
        LOGGER.error("trying to use a not yet implemented method");
        throw new Exception(HttpStatus.NOT_IMPLEMENTED.getReasonPhrase());
    }


    @Override
    @DeleteMapping(value = "/deleteById/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteById(@PathVariable Integer id) throws Exception {
        LOGGER.error("trying to use a not yet implemented method");
        throw new Exception(HttpStatus.NOT_IMPLEMENTED.getReasonPhrase());
    }

}
*/
