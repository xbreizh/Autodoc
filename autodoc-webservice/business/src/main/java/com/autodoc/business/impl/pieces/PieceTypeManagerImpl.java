package com.autodoc.business.impl.pieces;

import com.autodoc.business.contract.pieces.PieceTypeManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.pieces.PieceTypeDaoImpl;
import com.autodoc.model.models.pieces.PieceType;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class PieceTypeManagerImpl<T, D> extends AbstractGenericManager implements PieceTypeManager {
    private PieceTypeDaoImpl<PieceType> pieceTypeDao;
    private Logger logger = Logger.getLogger(PieceTypeManagerImpl.class);
    private ModelMapper mapper;

    public PieceTypeManagerImpl(PieceTypeDaoImpl<PieceType> pieceTypeDao) {
        super(pieceTypeDao);
        this.mapper = new ModelMapper();
        this.pieceTypeDao = pieceTypeDao;
    }


    @Override
    public Object entityToDto(Object entity) {
        return null;
    }

    @Override
    public Object dtoToEntity(Object entity) {
        return null;
    }


}
