package com.autodoc.controllers.impl.bill;


import com.autodoc.business.contract.IGenericManager;
import com.autodoc.business.contract.bill.BillManager;
import com.autodoc.controllers.contract.bill.BillController;
import com.autodoc.controllers.impl.GlobalControllerImpl;
import com.autodoc.controllers.impl.exceptions.NotImplementedmethodException;
import com.autodoc.model.dtos.bill.BillDTO;
import com.autodoc.model.models.bill.Bill;
import lombok.Builder;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bills")
@Builder
public class BillControllerImpl extends GlobalControllerImpl<Bill, BillDTO> implements BillController {
    private static final Logger LOGGER = Logger.getLogger(BillControllerImpl.class);
    private BillManager billManager;


    public IGenericManager getManager() {
        return billManager;
    }

    @DeleteMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteById(@PathVariable Integer id)  throws NotImplementedmethodException{
        throw new NotImplementedmethodException(HttpStatus.NOT_IMPLEMENTED.getReasonPhrase());
    }


}
