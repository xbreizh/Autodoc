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
import static org.mockito.ArgumentMatchers.anyInt;
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
    PieceType pieceType;

    @BeforeEach
    void init() {
        dao = mock(PieceDaoImpl.class);
        pieceTypedao = mock(PieceTypeDaoImpl.class);
        manager = PieceManagerImpl.builder().dao(dao).pieceTypeDao(pieceTypedao).build();
        provider = Provider.builder().firstName("john").lastName("bonham").website("jbonham.com").phoneNumber("abc1232434").build();
        obj = Piece.builder().name(name).brand(brand).id(id).quantity(quantity).buyingPrice(buyingPrice).sellPrice(sellPrice).provider(provider).build();
        dto = PieceDTO.builder().name(name).pieceTypeId(23).brand(brand).id(id).quantity(quantity).buyingPrice(buyingPrice).sellPrice(sellPrice).build();
        pieceType = PieceType.builder().name("pieceTypeStuff").id(2).build();
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

    @Test
    @DisplayName("should return normal name if quantity > 0")
    void updateNameAccordingToStock() {
        when(dao.getById(anyInt())).thenReturn(obj);
        assertEquals(obj.getName().toUpperCase(), manager.updateNameAccordingToStock(3));

    }

    @Test
    @DisplayName("should return OOS + normal name if quantity <= 0 and name wasn't OOS")
    void updateNameAccordingToStock1() {
        obj.setQuantity(0);
        when(dao.getById(anyInt())).thenReturn(obj);
        assertEquals("OOS " + obj.getName().toUpperCase(), manager.updateNameAccordingToStock(3));

    }

    @Test
    @DisplayName("should return OOS + normal name if quantity <= 0 and name was OOS")
    void updateNameAccordingToStock4() {
        obj.setQuantity(0);
        String name = obj.getName();
        obj.setName("OOS " + name);
        when(dao.getById(anyInt())).thenReturn(obj);
        assertEquals(obj.getName().toUpperCase(), manager.updateNameAccordingToStock(3));

    }

    @Test
    @DisplayName("should return normal name if quantity > 0 name has OOS")
    void updateNameAccordingToStock2() {
        Piece p = obj;
        name = p.getName();
        p.setName("OOS " + name);
        p.setQuantity(2);
        when(dao.getById(anyInt())).thenReturn(p);
        assertEquals(name.toUpperCase(), manager.updateNameAccordingToStock(3));

    }

    @Test
    @DisplayName("should return normal name if quantity > 0 name has not OOS")
    void updateNameAccordingToStock3() {
        obj.setQuantity(2);
        when(dao.getById(anyInt())).thenReturn(obj);
        assertEquals(name.toUpperCase(), manager.updateNameAccordingToStock(3));

    }

    @Test
    @DisplayName("should throw an exception if invalid pieceType")
    void transferPieceType() {
        when(pieceTypedao.getById(anyInt())).thenReturn(null);
        assertThrows(InvalidDtoException.class, () -> manager.transferPieceType(dto, obj));
    }

    @Test
    @DisplayName("should transfer pieceType")
    void transferPieceType1() {
        when(pieceTypedao.getById(anyInt())).thenReturn(pieceType);
        manager.transferPieceType(dto, obj);
        assertEquals(pieceType, obj.getPieceType());
    }

    @Test
    @DisplayName("should not transfer pieceType if pieceTypeId = 0")
    void transferPieceType2() {
        PieceType pieceType = obj.getPieceType();
        dto.setPieceTypeId(0);
        manager.transferPieceType(dto, obj);
        assertEquals(pieceType, obj.getPieceType());
    }

    @Test
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
    @DisplayName("should add if sign is +")
    void updateQuantity() {
        int quantity = 2;
        obj.setQuantity(quantity);
        when(dao.getById(anyInt())).thenReturn(obj);
        assertEquals(quantity + 1, manager.updateQuantity(obj, "+").getQuantity());
    }

    @Test
    @DisplayName("should lower if sign is -")
    void updateQuantity1() {
        int quantity = 2;
        obj.setQuantity(quantity);
        when(dao.getById(anyInt())).thenReturn(obj);
        assertEquals(quantity - 1, manager.updateQuantity(obj, "-").getQuantity());
    }

    @Test
    @DisplayName("should lower if sign is differente from - and +")
    void updateQuantity2() {
        int quantity = 2;
        obj.setQuantity(quantity);
        when(dao.getById(anyInt())).thenReturn(obj);
        assertEquals(quantity, manager.updateQuantity(obj, "=").getQuantity());
    }


    @Test
    @DisplayName("should throw an exception if id equals 0")
    void update() {
        dto.setId(0);
        assertThrows(InvalidDtoException.class, () -> manager.transferUpdate(dto));

    }

    @Test
    @DisplayName("should throw an exception if invalid id")
    void update1() {
        when(dao.getById(anyInt())).thenReturn(null);
        assertThrows(InvalidDtoException.class, () -> manager.transferUpdate(dto));

    }


    @Test
    @DisplayName("should update")
    void update2() {
        when(dao.getById(anyInt())).thenReturn(obj);
        when(pieceTypedao.getById(anyInt())).thenReturn(pieceType);
        obj = (Piece) manager.transferUpdate(dto);
        assertAll(
                () -> assertEquals(dto.getName().toUpperCase(), obj.getName()),
                () -> assertEquals(dto.getBrand().toUpperCase(), obj.getBrand()),
                () -> assertEquals(dto.getBuyingPrice(), obj.getBuyingPrice()),
                () -> assertEquals(dto.getSellPrice(), obj.getSellPrice()),
                () -> assertEquals(dto.getQuantity(), obj.getQuantity()),
                () -> assertDoesNotThrow(() -> manager.transferUpdate(dto))
        );

    }

}
