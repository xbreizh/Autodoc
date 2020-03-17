package com.autodoc.impl;

import com.autodoc.contract.PieceTypeService;
import com.autodoc.model.dtos.pieces.PieceTypeDTO;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
public class PieceTypeServiceImpl extends GlobalServiceImpl<PieceTypeDTO> implements PieceTypeService {
    private static Logger LOGGER = Logger.getLogger(PieceTypeServiceImpl.class);

    Class getObjectClass() {
        return PieceTypeDTO.class;
    }
    Class getListClass() {
        return PieceTypeDTO[].class;
    }


    public String getClassName() {
        return "pieceTypes";
    }



}

