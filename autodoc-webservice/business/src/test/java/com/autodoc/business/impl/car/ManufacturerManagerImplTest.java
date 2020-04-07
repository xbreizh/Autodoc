package com.autodoc.business.impl.car;

import com.autodoc.business.contract.car.ManufacturerManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.dao.impl.car.ManufacturerDaoImpl;
import com.autodoc.model.dtos.car.ManufacturerDTO;
import com.autodoc.model.models.car.Manufacturer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ManufacturerManagerImplTest {

    private ManufacturerManager manager;
    private ManufacturerDaoImpl dao;
    private String name = "Bob";
    private Manufacturer obj;
    private ManufacturerDTO dto;
    private int id = 22;

    @BeforeEach
    void init() {
        dao = mock(ManufacturerDaoImpl.class);
        manager = ManufacturerManagerImpl.builder().dao(dao).build();
        obj = Manufacturer.builder().id(id).name(name).build();
        dto = ManufacturerDTO.builder().name("niamo").build();
    }

    @Test
    @DisplayName("should return obj if existing")
    void getDao() {
        assertEquals(dao, manager.getDao());

    }


    @Test
    @DisplayName("should convert dto into entity")
    void dtoToEntity() {
        obj = (Manufacturer) manager.dtoToEntity(dto);
        System.out.println(obj);
        System.out.println(dto);
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
    @DisplayName("should return obj if existing")
    void getByName() {
        when(dao.getByName(anyString())).thenReturn(obj);
        assertAll(
                () -> assertEquals(name.toUpperCase(), manager.getByName(name).getName()),
                () -> assertEquals(id, manager.getByName(name).getId())
        );

    }

    @Test
    @DisplayName("should return null if not existing")
    void getByName1() {
        assertNull(manager.getByName(""));

    }

    @Test
    @DisplayName("should throw an exception if name empty")
    void checkIfDuplicate() {
        dto.setName("");
        assertThrows(InvalidDtoException.class, () -> manager.checkIfDuplicate(dto));
    }

    @Test
    @DisplayName("should throw an exception if name null")
    void checkIfDuplicate1() {
        dto.setName(null);
        assertThrows(InvalidDtoException.class, () -> manager.checkIfDuplicate(dto));
    }

    @Test
    @DisplayName("should throw an exception if existing name")
    void checkIfDuplicate2() {
        when(dao.getByName(anyString())).thenReturn(obj);
        assertThrows(InvalidDtoException.class, () -> manager.checkIfDuplicate(dto));
    }

    @Test
    @DisplayName("should not throw an exception if name ok")
    void checkIfDuplicate3() {
        dto.setName("djabukie");
        assertDoesNotThrow(() -> manager.checkIfDuplicate(dto));
    }


}
