package com.autodoc.business.impl.pieces;

import com.autodoc.business.contract.pieces.PieceManager;
import com.autodoc.dao.impl.pieces.PieceDaoImpl;
import com.autodoc.model.models.pieces.Piece;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
public class PieceManagerImpl implements PieceManager {
    private PieceDaoImpl<Piece> pieceDao;
    private Logger logger = Logger.getLogger(PieceManagerImpl.class);


    public PieceManagerImpl(PieceDaoImpl<Piece> pieceDao) {
        this.pieceDao = pieceDao;

    }


    @Override
    public String save(Piece piece) {
        pieceDao.create(piece);
        return "piece added";
    }

    @Override
    public List<Piece> getAll() {
        return null;
    }
}
