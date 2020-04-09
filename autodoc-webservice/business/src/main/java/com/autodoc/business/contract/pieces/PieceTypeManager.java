package com.autodoc.business.contract.pieces;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.dtos.pieces.PieceTypeDTO;
import com.autodoc.model.models.pieces.PieceType;

public interface PieceTypeManager extends IGenericManager {

    PieceType checkIfExistingPieceType(PieceTypeDTO dto);

}
