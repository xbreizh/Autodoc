package com.autodoc.impl;

import com.autodoc.contract.BillService;
import com.autodoc.model.dtos.bill.BillDTO;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
public class BillServiceImpl extends GlobalServiceImpl<BillDTO> implements BillService {
    private static Logger LOGGER = Logger.getLogger(BillServiceImpl.class);

    Class getObjectClass() {
        return BillDTO.class;
    }

    Class getListClass() {
        return BillDTO[].class;
    }

    public String getClassName() {
        return "bills";
    }


}

