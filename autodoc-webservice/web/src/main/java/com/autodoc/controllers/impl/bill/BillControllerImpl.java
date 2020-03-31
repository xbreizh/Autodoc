package com.autodoc.controllers.impl.bill;


import com.autodoc.business.contract.IGenericManager;
import com.autodoc.business.contract.bill.BillManager;
import com.autodoc.controllers.contract.bill.BillController;
import com.autodoc.controllers.impl.GlobalControllerImpl;
import com.autodoc.model.dtos.bill.BillDTO;
import com.autodoc.model.models.bill.Bill;
import lombok.Builder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bills")
@Builder
public class BillControllerImpl extends GlobalControllerImpl<Bill, BillDTO> implements BillController {
    private static final Logger LOGGER = Logger.getLogger(BillControllerImpl.class);
    private BillManager billManager;


    public IGenericManager getManager() {
        return billManager;
    }


}
