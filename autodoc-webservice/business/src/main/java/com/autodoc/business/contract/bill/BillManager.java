package com.autodoc.business.contract.bill;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.models.pieces.Piece;

import java.util.List;

/*@Service*/
public interface BillManager extends IGenericManager {

    void updateStockAndAddPieces(List<Piece> pieces, List<Piece> pieceFromDb);
}
