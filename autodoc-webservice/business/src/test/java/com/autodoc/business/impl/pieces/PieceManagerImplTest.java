package com.autodoc.business.impl.pieces;

import com.autodoc.business.contract.pieces.PieceManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.dao.contract.pieces.PieceDao;
import com.autodoc.dao.contract.pieces.PieceTypeDao;
import com.autodoc.dao.impl.pieces.PieceDaoImpl;
import com.autodoc.dao.impl.pieces.PieceTypeDaoImpl;
import com.autodoc.model.dtos.pieces.PieceDTO;
import com.autodoc.model.models.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class PieceManagerImplTest {

    PieceManager pieceManager;
    PieceTypeDao pieceTypedao;
    PieceDao pieceDao;

    @BeforeEach
    void init() {
        pieceDao = mock(PieceDaoImpl.class);
        pieceTypedao = mock(PieceTypeDaoImpl.class);
        pieceManager = new PieceManagerImpl<Piece, PieceDTO>(pieceDao, pieceTypedao);

    }

    @Test
    @DisplayName("should throw an exception if selling price is lower than buying price")
    void checkPrices() {
        Piece piece = new Piece();
        piece.setBuyingPrice(12.15);
        piece.setSellPrice(10);
        assertThrows(InvalidDtoException.class, () -> pieceManager.checkSellingPriceIsEqualOrHigherBuyingPrice(piece));
    }

    @Test
    @DisplayName("should not throw an exception if selling price is equal to buying price")
    void checkPrices1() {
        Piece piece = new Piece();
        piece.setBuyingPrice(12.15);
        piece.setSellPrice(12.5);
        assertDoesNotThrow(() -> pieceManager.checkSellingPriceIsEqualOrHigherBuyingPrice(piece));
    }

}