package com.autodoc.business.impl.pieces;

import com.autodoc.business.contract.pieces.PieceManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.pieces.PieceDaoImpl;
import com.autodoc.model.dtos.pieces.PieceDTO;
import com.autodoc.model.models.pieces.Piece;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class PieceManagerImpl<T, D> extends AbstractGenericManager implements PieceManager {
    private PieceDaoImpl<Piece> pieceDao;
    private static final Logger LOGGER = Logger.getLogger(PieceManagerImpl.class);
    private ModelMapper mapper;

    public PieceManagerImpl(PieceDaoImpl<Piece> pieceDao) {
        super(pieceDao);
        this.mapper = new ModelMapper();
        this.pieceDao = pieceDao;
    }


    @Override
    public PieceDTO entityToDto(Object entity) {
        PieceDTO dto = mapper.map(entity, PieceDTO.class);
        LOGGER.info("converted into dto");
        return dto;
    }

    @Override
    public Piece dtoToEntity(Object entity) throws Exception {
        PieceDTO dto = (PieceDTO) entity;
        Piece piece = mapper.map(entity, Piece.class);
        checkDataInsert(dto);
        return piece;
    }


}
