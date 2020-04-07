package com.autodoc.business.impl.person.provider;

import com.autodoc.business.contract.person.provider.CountryManager;
import com.autodoc.dao.impl.person.provider.CountryDaoImpl;
import com.autodoc.model.dtos.person.provider.CountryDTO;
import com.autodoc.model.models.person.provider.Country;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CountryManagerImplTest {

    private static final Logger LOGGER = Logger.getLogger(CountryManagerImplTest.class);
    private CountryManager manager;
    private CountryDaoImpl dao;
    private Country obj;
    private String name = "portugal";
    private String nameDto = "spain";
    private CountryDTO dto;
    private int id = 323;


    @BeforeEach
    void init() {
        dao = mock(CountryDaoImpl.class);
        manager = CountryManagerImpl.builder().dao(dao).build();
        obj = Country.builder().id(id).name(name).build();
        dto = CountryDTO.builder().name(nameDto).build();
    }

    @Test
    @DisplayName("should return obj if existing")
    void getDao() {
        assertEquals(dao, manager.getDao());

    }

    @Test
    @DisplayName("should convert dto into entity")
    void dtoToEntity() {
        obj = (Country) manager.dtoToEntity(dto);
        assertAll(
                () -> assertEquals(dto.getName().toUpperCase(), obj.getName()),
                () -> assertEquals(dto.getId(), obj.getId())
        );

    }


    @Test
    @DisplayName("should return null if entity is null")
    void dtoToEntity1() throws Exception {
        assertNull(manager.dtoToEntity(null));

    }

    @Test
    @DisplayName("should convert dto into entity")
    void entityToDto() throws Exception {
        dto = (CountryDTO) manager.entityToDto(obj);
        assertAll(
                () -> assertEquals(dto.getName().toUpperCase(), obj.getName()),
                () -> assertEquals(dto.getId(), obj.getId())
        );

    }


    @Test
    @DisplayName("should return null if entity is null")
    void entityToDto1() {
        assertNull(manager.entityToDto(null));

    }


}
