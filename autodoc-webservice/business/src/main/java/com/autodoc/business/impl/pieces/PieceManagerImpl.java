package com.autodoc.business.impl.pieces;

import com.autodoc.business.contract.pieces.PieceManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.pieces.PieceDaoImpl;
import com.autodoc.model.models.pieces.Piece;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class PieceManagerImpl<T, D> extends AbstractGenericManager implements PieceManager {
    private PieceDaoImpl<Piece> pieceDao;
    private Logger logger = Logger.getLogger(PieceManagerImpl.class);

    public PieceManagerImpl(PieceDaoImpl<Piece> pieceDao) {
        super(pieceDao);
        this.pieceDao = pieceDao;
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
