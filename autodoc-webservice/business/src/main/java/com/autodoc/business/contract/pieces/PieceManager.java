package com.autodoc.business.contract.pieces;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.dtos.pieces.PieceDTO;
import com.autodoc.model.models.pieces.Piece;

import java.util.List;

public interface PieceManager extends IGenericManager {

    void checkSellingPriceIsEqualOrHigherBuyingPrice(Piece piece);

    List<Piece> updateStockAndAddPieces(List<Piece> pieces, List<Piece> pieceFromDb);

    String updateNameAccordingToStock(int id);

    Piece updateQuantity(Piece piece, String sign);

    void checkThatPieceIdIsNotNull(PieceDTO dto);

    void transferPieceType(PieceDTO dto, Piece piece);

}
