package com.autodoc.business.contract.pieces;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.models.pieces.PieceType;

public interface PieceTypeManager extends IGenericManager {

    String save(PieceType pieceType);

    //  List<PieceType> getAll();
}
