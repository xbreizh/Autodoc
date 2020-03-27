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
    Piece piece;
    String name = "tezst stock";
    String brand = "brandymo";
    int id = 44;
    int quantity = 23;
    double sellPrice = 123;
    double buyingPrice = 22;

    @BeforeEach
    void init() {
        pieceDao = mock(PieceDaoImpl.class);
        pieceTypedao = mock(PieceTypeDaoImpl.class);
        pieceManager = new PieceManagerImpl<Piece, PieceDTO>(pieceDao, pieceTypedao);

    }

    @BeforeEach
    void createDummyPiece() {
        piece = new Piece();
        piece.setName(name);
        piece.setBrand(brand);
        piece.setId(id);
        piece.setQuantity(quantity);
        piece.setSellPrice(sellPrice);
        piece.setBuyingPrice(buyingPrice);
    }


    @Test
    @DisplayName("should transfer data from entity to dto")
    void entityToDto() {
        PieceDTO dto = new PieceDTO();
        dto = (PieceDTO) pieceManager.entityToDto(piece);
        //assertDoesNotThrow(()->pieceManager.entityToDto(piece));
        System.out.println("dto: " + dto);
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

/*    @Test
    @DisplayName("should add OOS if quantity lower or equals 0")
    void updateNameAccordingToStock() {
        String oos = "OOS ";
        String name = "PLAQUETTE DE FREINS";
        Piece piece = new Piece();
        piece.setName(name);
        piece.setQuantity(-2);
        pieceManager.updateNameAccordingToStock(piece);
        assertEquals(oos + name, piece.getName());

    }

    @Test
    @DisplayName("should keep OOS if quantity lower or equals 0")
    void updateNameAccordingToStock3() {
        String oos = "OOS ";
        String name = "PLAQUETTE DE FREINS";
        Piece piece = new Piece();
        piece.setName(oos + name);
        piece.setQuantity(-2);
        pieceManager.updateNameAccordingToStock(piece);
        assertEquals(oos + name, piece.getName());

    }

    @Test
    @DisplayName("should remove OOS if quantity was low and is not anymore")
    void updateNameAccordingToStock1() {
        String oos = "OOS ";
        String name = "OOS PLAQUETTE DE FREINS";
        Piece piece = new Piece();
        piece.setName(oos + name);
        piece.setQuantity(2);
        pieceManager.updateNameAccordingToStock(piece);
        assertEquals(name, piece.getName());

    }

    @Test
    @DisplayName("should not update name if stock was and remains above 0")
    void updateNameAccordingToStock2() {
        String name = "PLAQUETTE DE FREINS";
        Piece piece = new Piece();
        piece.setName(name);
        piece.setQuantity(2);
        pieceManager.updateNameAccordingToStock(piece);
        assertEquals(name, piece.getName());

    }*/


    @Test
    void updateQuantity() {
        Piece piece = new Piece();
        piece.setQuantity(2);
        System.out.println(pieceManager.updateQuantity(piece, "-"));


    }
}