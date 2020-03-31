package com.autodoc.business.impl;


import com.autodoc.business.contract.PieceManager;
import com.autodoc.business.contract.PieceTypeManager;
import com.autodoc.business.exceptions.ObjectFormattingException;
import com.autodoc.contract.GlobalService;
import com.autodoc.contract.PieceService;
import com.autodoc.model.dtos.pieces.PieceDTO;
import com.autodoc.model.dtos.pieces.PieceForm;
import com.autodoc.model.models.pieces.Piece;
import com.autodoc.model.models.pieces.PieceType;
import lombok.Builder;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
@Builder
public class PieceManagerImpl extends GlobalManagerImpl<Piece, PieceDTO> implements PieceManager {

    private static final Logger LOGGER = Logger.getLogger(PieceManagerImpl.class);
    PieceService service;
    // public Piece piece;
    private PieceTypeManager pieceTypeManager;

    GlobalService getService() {
        return service;
    }

    public Piece dtoToEntity(String token, Object obj) throws Exception {

        PieceDTO dto = (PieceDTO) obj;
        LOGGER.info("dto: " + dto);
        Piece piece = new Piece();
        int id = dto.getId();
        LOGGER.info("id: " + id);
        piece.setId(id);
        piece.setName(dto.getName());
        PieceType pieceType = (PieceType) pieceTypeManager.getById(token, dto.getPieceTypeId());
        if (pieceType == null) throw new ObjectFormattingException("invalid pieceTypeId: " + dto.getPieceTypeId());
        piece.setPieceType(pieceType);
        piece.setBrand(dto.getBrand());
        piece.setBuyingPrice(dto.getBuyingPrice());
        piece.setQuantity(dto.getQuantity());
        piece.setSellPrice(dto.getSellPrice());
        LOGGER.info("entity transferred: " + piece);

        return piece;
    }

    public PieceDTO formToDto(Object obj, String token) {
        LOGGER.info("stuff to update: " + obj);
        PieceForm form = (PieceForm) obj;
        LOGGER.info("dto: " + form);
        PieceDTO dto = new PieceDTO();
        if (form.getId() != 0) dto.setId(form.getId());
        if (form.getBuyingPrice() != 0) dto.setBuyingPrice(form.getBuyingPrice());
        if (form.getSellPrice() != 0) dto.setSellPrice(form.getSellPrice());
        if (form.getBrand() != null) dto.setBrand(form.getBrand());
        if (form.getName() != null) dto.setName(form.getName());
        dto.setQuantity(form.getQuantity());
        if (form.getPieceTypeId() != 0) dto.setPieceTypeId(form.getPieceTypeId());
        return dto;
    }

   /* public List<Piece> convertList(List<Object> list, String token) throws Exception {
        LOGGER.info("converting list");
        List<Piece> newList = new ArrayList<>();
        for (Object obj : list) {
            PieceDTO dto = (PieceDTO) obj;
            Piece piece = dtoToEntity(token, dto.getId());

            newList.add(piece);
        }
        return newList;
    }*/

}
