package com.autodoc.business.contract.bill;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.dtos.bill.BillDTO;
import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.pieces.Piece;

import java.text.SimpleDateFormat;
import java.util.List;

/*@Service*/
public interface BillManager extends IGenericManager {

    void updateStockAndAddOrRemovePiecesFromStock(List<Piece> pieces, List<Piece> pieceFromDb);

    SimpleDateFormat getDateFormat();

    void updateBillStatusIfMissingPiece(Bill bill);

    void transferEmployee(BillDTO dto, Bill bill);

    void transferClient(BillDTO dto, Bill bill);

    void transferPieces(BillDTO dto, Bill bill);

    void transferTasks(BillDTO dto, Bill bill);

    void transferCar(BillDTO dto, Bill bill);

    void transferDateReparation(BillDTO dto, Bill bill);
}
