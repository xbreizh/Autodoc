package com.autodoc.business.impl.pieces;

import com.autodoc.business.contract.pieces.PieceTypeManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.pieces.PieceTypeDaoImpl;
import com.autodoc.model.dtos.pieces.PieceTypeDTO;
import com.autodoc.model.models.pieces.PieceType;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class PieceTypeManagerImpl<T, D> extends AbstractGenericManager implements PieceTypeManager {
    private PieceTypeDaoImpl<PieceType> pieceTypeDao;
    private static final Logger LOGGER = Logger.getLogger(PieceTypeManagerImpl.class);
    private ModelMapper mapper;

    public PieceTypeManagerImpl(PieceTypeDaoImpl<PieceType> pieceTypeDao) {
        super(pieceTypeDao);
        this.mapper = new ModelMapper();
        this.pieceTypeDao = pieceTypeDao;
    }


    @Override
    public PieceTypeDTO entityToDto(Object entity) {
        PieceTypeDTO dto = mapper.map(entity, PieceTypeDTO.class);
        LOGGER.info("converted into dto");
        return dto;
    }

    @Override
    public PieceType dtoToEntity(Object entity) throws Exception {
        PieceTypeDTO dto = (PieceTypeDTO) entity;
        PieceType pieceType = mapper.map(entity, PieceType.class);
        checkDataInsert(dto);
        return pieceType;
    }


}
