package com.autodoc.business.impl.pieces;

import com.autodoc.business.contract.pieces.PieceTypeManager;
import com.autodoc.dao.impl.pieces.PieceTypeDaoImpl;
import com.autodoc.model.models.pieces.PieceType;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
public class PieceTypeManagerImpl implements PieceTypeManager {
    private PieceTypeDaoImpl<PieceType> pieceTypeDao;
    private Logger logger = Logger.getLogger(PieceTypeManagerImpl.class);


    public PieceTypeManagerImpl(PieceTypeDaoImpl<PieceType> pieceTypeDao) {
        this.pieceTypeDao = pieceTypeDao;

    }


    @Override
    public String save(PieceType pieceType) {
        pieceTypeDao.create(pieceType);
        return "piece added";
    }

    @Override
    public List<PieceType> getAll() {
        return null;
    }
}
