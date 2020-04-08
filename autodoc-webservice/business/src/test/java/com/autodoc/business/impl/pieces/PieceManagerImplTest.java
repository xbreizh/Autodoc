package com.autodoc.business.impl.pieces;

import com.autodoc.business.contract.pieces.PieceManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.dao.contract.pieces.PieceDao;
import com.autodoc.dao.contract.pieces.PieceTypeDao;
import com.autodoc.dao.impl.pieces.PieceDaoImpl;
import com.autodoc.dao.impl.pieces.PieceTypeDaoImpl;
import com.autodoc.model.dtos.pieces.PieceDTO;
import com.autodoc.model.models.person.provider.Provider;
import com.autodoc.model.models.pieces.Piece;
import com.autodoc.model.models.pieces.PieceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PieceManagerImplTest {

    PieceManager manager;
    PieceTypeDao pieceTypedao;
    PieceDao dao;
    Piece obj;
    PieceDTO dto;
    String name = "tezst stock";
    String brand = "brandymo";
    int id = 44;
    int quantity = 23;
    double sellPrice = 123;
    double buyingPrice = 22;
    Provider provider;

    @BeforeEach
    void init() {
        dao = mock(PieceDaoImpl.class);
        pieceTypedao = mock(PieceTypeDaoImpl.class);
        manager = PieceManagerImpl.builder().dao(dao).pieceTypeDao(pieceTypedao).build();
        provider = Provider.builder().firstName("john").lastName("bonham").website("jbonham.com").phoneNumber("abc1232434").build();
        obj = Piece.builder().name(name).brand(brand).id(id).quantity(quantity).buyingPrice(buyingPrice).sellPrice(sellPrice).provider(provider).build();
        dto = PieceDTO.builder().name(name).pieceTypeId(23).brand(brand).id(id).quantity(quantity).buyingPrice(buyingPrice).sellPrice(sellPrice).build();
    }


    @Test
    @DisplayName("should return obj if existing")
    void getDao() {
        assertEquals(dao, manager.getDao());

    }

    @Test
    @DisplayName("should transfer data from entity to dto")
    void entityToDto() {
        PieceType pieceType = PieceType.builder().id(23).name("dfefe").build();
        obj.setPieceType(pieceType);
        dto = (PieceDTO) manager.entityToDto(obj);
        System.out.println("dto: " + dto);
        assertAll(
                () -> assertEquals(name.toUpperCase(), dto.getName()),
                () -> assertEquals(brand.toUpperCase(), dto.getBrand()),
                () -> assertEquals(buyingPrice, dto.getBuyingPrice()),
                () -> assertEquals(sellPrice, dto.getSellPrice()),
                () -> assertEquals(quantity, dto.getQuantity())
        );
    }

    @Test
    @DisplayName("should transfer data from dto to entity")
    void dtoToEntity() {
        obj = (Piece) manager.dtoToEntity(dto);
        assertAll(
                () -> assertEquals(name.toUpperCase(), obj.getName()),
                () -> assertEquals(brand.toUpperCase(), obj.getBrand()),
                () -> assertEquals(buyingPrice, obj.getBuyingPrice()),
                () -> assertEquals(sellPrice, obj.getSellPrice()),
                () -> assertEquals(quantity, obj.getQuantity())
        );
    }


    @Test
    @DisplayName("should throw an exception if name already exist")
    void transferInsert() {
        when(dao.getByName(anyString())).thenReturn(obj);
        assertThrows(InvalidDtoException.class, () -> manager.transferInsert(dto));
    }

    @Test
    @DisplayName("should not throw an exception ")
    void transferInsert1() {
        when(dao.getByName(anyString())).thenReturn(null);
        obj = (Piece) manager.transferInsert(dto);
        assertAll(
                () -> assertEquals(name.toUpperCase(), obj.getName()),
                () -> assertEquals(brand.toUpperCase(), obj.getBrand()),
                () -> assertEquals(buyingPrice, obj.getBuyingPrice()),
                () -> assertEquals(sellPrice, obj.getSellPrice()),
                () -> assertEquals(quantity, obj.getQuantity()),
                () -> assertDoesNotThrow(() -> manager.transferInsert(dto))
        );

    }

    @Test
    @DisplayName("should throw an exception if pieceTypeId is equals to 0")
    void transferInsert2() {
        dto.setPieceTypeId(0);
        when(dao.getByName(anyString())).thenReturn(null);
        assertThrows(InvalidDtoException.class, () -> manager.transferInsert(dto));
    }

    @Test
    @DisplayName("should throw an exception if pieceTypeId is equals to 0")
    void checkThatProviderIdIsNotNull() {
        dto.setPieceTypeId(0);
        when(dao.getByName(anyString())).thenReturn(null);
        assertThrows(InvalidDtoException.class, () -> manager.checkThatPieceIdIsNotNull(dto));
    }

 /*   @Test
    @DisplayName("should throw an exception if selling price is lower than buying price")
    void checkPrices() {
        Piece piece = new Piece();
        piece.setBuyingPrice(12.15);
        piece.setSellPrice(10);
        assertThrows(InvalidDtoException.class, () -> manager.checkSellingPriceIsEqualOrHigherBuyingPrice(piece));
    }

    @Test
    @DisplayName("should not throw an exception if selling price is equal to buying price")
    void checkPrices1() {
        Piece piece = new Piece();
        piece.setBuyingPrice(12.15);
        piece.setSellPrice(12.5);
        assertDoesNotThrow(() -> manager.checkSellingPriceIsEqualOrHigherBuyingPrice(piece));
    }

    @Test
    @DisplayName("should add OOS if quantity lower or equals 0")
    void updateNameAccordingToStock() {
        String oos = "OOS ";
        String name = "PLAQUETTE DE FREINS";
        Piece piece = new Piece();
        piece.setName(name);
        piece.setQuantity(-2);
        manager.updateNameAccordingToStock(piece);
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
        manager.updateNameAccordingToStock(piece);
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
        manager.updateNameAccordingToStock(piece);
        assertEquals(name, piece.getName());

    }

    @Test
    @DisplayName("should not update name if stock was and remains above 0")
    void updateNameAccordingToStock2() {
        String name = "PLAQUETTE DE FREINS";
        Piece piece = new Piece();
        piece.setName(name);
        piece.setQuantity(2);
        manager.updateNameAccordingToStock(piece);
        assertEquals(name, piece.getName());

    }



    @Test
    void updateQuantity() {
        Piece piece = new Piece();
        piece.setQuantity(2);
        System.out.println(manager.updateQuantity(piece, "-"));


    }*/
}
