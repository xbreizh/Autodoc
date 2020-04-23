package com.autodoc.business.impl.pieces;

import com.autodoc.business.contract.pieces.PieceTypeManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.dao.impl.pieces.PieceTypeDaoImpl;
import com.autodoc.model.dtos.pieces.PieceTypeDTO;
import com.autodoc.model.models.pieces.PieceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PieceTypeManagerImplTest {

    private PieceTypeManager manager;
    private PieceTypeDaoImpl dao;
    private String name = "Bob";
    private PieceType obj;
    private PieceTypeDTO dto;
    private int id = 22;

    @BeforeEach
    void init() {
        dao = mock(PieceTypeDaoImpl.class);
        manager = PieceTypeManagerImpl.builder().dao(dao).build();
        obj = PieceType.builder().id(id).name(name).build();
        dto = PieceTypeDTO.builder().name("niamo").id(2).build();
    }

    @Test
    @DisplayName("should return obj if existing")
    void getDao() {
        assertEquals(dao, manager.getDao());

    }


    @Test
    @DisplayName("should convert dto into entity")
    void dtoToEntity() {
        obj = (PieceType) manager.dtoToEntity(dto);
        assertAll(
                () -> assertEquals(obj.getName(), dto.getName().toUpperCase()),
                () -> assertEquals(dto.getId(), obj.getId())
        );

    }

    @Test
    @DisplayName("should return null if entity is null")
    void dtoToEntity1() throws Exception {
        assertNull(manager.dtoToEntity(null));

    }

    @Test
    @DisplayName("should convert entity into dto")
    void entityToDto() {
        dto = (PieceTypeDTO) manager.entityToDto(obj);
        assertAll(
                () -> assertEquals(obj.getName(), dto.getName().toUpperCase()),
                () -> assertEquals(dto.getId(), obj.getId())
        );

    }

    @Test
    @DisplayName("should throw an error if duplicate")
    void checkIfDuplicate() {
        when(dao.getByName(dto.getName())).thenReturn(obj);
        assertThrows(InvalidDtoException.class, () -> manager.checkIfDuplicate(dto));
    }

    @Test
    @DisplayName("should not throw an error if no duplicate")
    void checkIfDuplicate1() {
        when(dao.getByName(dto.getName())).thenReturn(null);
        assertDoesNotThrow(() -> manager.checkIfDuplicate(dto));
    }

    @Test
    @DisplayName("should transfer object")
    void transferInsert() {
        obj = (PieceType) manager.transferInsert(dto);
        when(dao.getByName(dto.getName())).thenReturn(null);
        assertAll(
                () -> assertEquals(obj.getName(), dto.getName().toUpperCase()),
                () -> assertEquals(dto.getId(), obj.getId())
        );
    }

    @Test
    @DisplayName("should throw an exception if pieceType not existing")
    void checkIfExistingPieceType() {
        when(dao.getById(anyInt())).thenReturn(null);
        assertThrows(InvalidDtoException.class, () -> manager.checkIfExistingPieceType(dto));
    }

    @Test
    @DisplayName("should not throw an exception if pieceType  existing")
    void checkIfExistingPieceType1() {
        when(dao.getById(anyInt())).thenReturn(obj);
        assertDoesNotThrow(() -> manager.checkIfExistingPieceType(dto));
    }

    @Test
    @DisplayName("should update object")
    void transferUpdate() {
        when(dao.getById(anyInt())).thenReturn(obj);
        obj = (PieceType) manager.transferUpdate(dto);
        assertAll(
                () -> assertEquals(dto.getName().toUpperCase(), obj.getName().toUpperCase())
        );

    }

}