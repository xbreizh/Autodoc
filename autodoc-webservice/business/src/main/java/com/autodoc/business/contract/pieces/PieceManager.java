package com.autodoc.business.contract.pieces;

import com.autodoc.model.models.pieces.Piece;

import java.util.List;

public interface PieceManager {

    String save(Piece piece);

    List<Piece> getAll();
}
