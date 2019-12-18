package com.autodoc.business.impl;


import com.autodoc.business.contract.PieceTypeManager;
import com.autodoc.contract.PieceTypeService;
import com.autodoc.model.dtos.pieces.PieceTypeDTO;
import com.autodoc.model.dtos.pieces.PieceTypeForm;
import com.autodoc.model.models.pieces.PieceType;
import org.apache.log4j.Logger;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class PieceTypeManagerImpl extends GlobalManagerImpl<PieceType, PieceTypeDTO> implements PieceTypeManager {

    private static final Logger LOGGER = Logger.getLogger(PieceTypeManagerImpl.class);
    public PieceType pieceType;
    private PieceTypeService service;

    public PieceTypeManagerImpl(PieceTypeService service) {
        super(service);
        this.service = service;
    }

    public PieceType dtoToEntity(String token, Object obj) {

        PieceTypeDTO dto = (PieceTypeDTO) obj;
        LOGGER.info("dto: " + dto);
        PieceType pieceType = new PieceType();
        int id = dto.getId();
        LOGGER.info("id: " + id);
        pieceType.setId(id);
        pieceType.setName(dto.getName());
        LOGGER.info("entity transferred: " + pieceType);

        return pieceType;
    }

    public PieceTypeDTO formToDto(Object obj) {
        LOGGER.info("stuff to update: " + obj);
        PieceTypeForm dto = (PieceTypeForm) obj;
        LOGGER.info("dto: " + dto);
        PieceTypeDTO pieceType = new PieceTypeDTO();
        if (dto.getId() != 0) pieceType.setId(dto.getId());
        pieceType.setName(dto.getName());
        return pieceType;
    }

    public List<PieceType> convertList(List<Object> list) {
        LOGGER.info("converting list");
        List<PieceType> newList = new ArrayList<>();
        for (Object obj : list) {
            PieceTypeDTO dto = (PieceTypeDTO) obj;
            PieceType pieceType = new PieceType();
            int id = dto.getId();
            LOGGER.info("id: " + id);
            pieceType.setId(id);
            pieceType.setName(dto.getName());

            newList.add(pieceType);
        }
        return newList;
    }

}
