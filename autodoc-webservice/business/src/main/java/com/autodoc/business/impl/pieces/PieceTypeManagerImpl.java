package com.autodoc.business.impl.pieces;

import com.autodoc.business.contract.pieces.PieceTypeManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.dao.contract.pieces.PieceTypeDao;
import com.autodoc.model.dtos.pieces.PieceTypeDTO;
import com.autodoc.model.models.pieces.PieceType;
import lombok.Builder;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
@Builder
public class PieceTypeManagerImpl extends AbstractGenericManager implements PieceTypeManager {
    private static final Logger LOGGER = Logger.getLogger(PieceTypeManagerImpl.class);
    private static final ModelMapper mapper = new ModelMapper();
    private PieceTypeDao dao;

    @Override
    public IGenericDao getDao() {
        LOGGER.info("getting dao: ");

        return dao;
    }


    @Override
    public PieceTypeDTO entityToDto(Object entity) {
        PieceTypeDTO dto = mapper.map(entity, PieceTypeDTO.class);
        LOGGER.info("converted into dto");
        return dto;
    }

    @Override
    public Object dtoToEntity(Object entity) throws InvalidDtoException {
        throw new InvalidDtoException("error");

    }

    @Override
    public PieceType transferInsert(Object obj) throws InvalidDtoException {
        PieceTypeDTO dto = (PieceTypeDTO) obj;
        String name = dto.getName().toUpperCase();
        PieceType pieceType = new PieceType();
        pieceType.setName(name.toUpperCase());
        PieceType pieceTypeCheck = (PieceType) dao.getByName(name);
        if (pieceTypeCheck != null) throw new InvalidDtoException("that pieceType already exist");
        return pieceType;
    }

    @Override
    public void checkIfDuplicate(Object obj) throws InvalidDtoException {
        LOGGER.info("just passing");

    }

    public PieceType transferUpdate(Object obj) throws InvalidDtoException {
        PieceTypeDTO dto = (PieceTypeDTO) obj;
        int id = dto.getId();
        PieceType pieceType = (PieceType) dao.getById(dto.getId());
        if (pieceType == null) throw new InvalidDtoException("pieceType invalid id: " + id);
        pieceType.setName(dto.getName().toUpperCase());
        return pieceType;
    }


}
