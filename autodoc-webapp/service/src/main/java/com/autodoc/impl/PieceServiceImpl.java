package com.autodoc.impl;

import com.autodoc.contract.PieceService;
import com.autodoc.model.dtos.pieces.PieceDTO;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
public class PieceServiceImpl extends GlobalServiceImpl<PieceDTO> implements PieceService {
    private static Logger LOGGER = Logger.getLogger(PieceServiceImpl.class);

    Class getObjectClass() {
        return PieceDTO.class;
    }
    Class getListClass() {
        return PieceDTO[].class;
    }


    public String getClassName() {
        return "pieces";
    }


}

