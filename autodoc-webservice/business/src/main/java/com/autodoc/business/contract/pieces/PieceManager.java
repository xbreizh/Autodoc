package com.autodoc.business.contract.pieces;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.models.pieces.Piece;

public interface PieceManager extends IGenericManager {

    void checkSellingPriceIsEqualOrHigherBuyingPrice(Piece piece);
}
