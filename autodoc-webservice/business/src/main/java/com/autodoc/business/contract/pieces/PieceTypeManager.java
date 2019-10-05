package com.autodoc.business.contract.pieces;

import com.autodoc.model.models.pieces.PieceType;

import java.util.List;

public interface PieceTypeManager {

    String save(PieceType pieceType);

    List<PieceType> getAll();
}
