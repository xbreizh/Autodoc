package com.autodoc.business.impl;

import com.autodoc.dao.contract.car.CarModelDao;
import com.autodoc.dao.impl.car.CarModelDaoImpl;
import com.autodoc.model.dtos.car.CarModelDTO;
import com.autodoc.model.enums.FuelType;
import com.autodoc.model.models.car.CarModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class AbstractGenericManagerTest {

    private CarModel obj;
    private CarModelDTO dto;
    private AbstractGenericManagerPojo manager;
    private CarModelDao dao;
    private int id = 22;
    private String name = "Bob";
    private String nameDto = "niamo";
    private String description = "descriere";
    private String engine = "12324AS";


    @BeforeEach
    void init() {
        dao = mock(CarModelDaoImpl.class);
        manager = AbstractGenericManagerPojo.builder().dao(dao).build();
        obj = CarModel.builder().id(id).name(name).build();
        dto = CarModelDTO.builder().name(nameDto).description(description).fuelType(FuelType.PETROL).engine(engine).build();
    }

    @Test
    @DisplayName("should return obj if existing")
    void getDao() {
        assertNull(manager.getDao());

    }

    @Test
    @DisplayName("should return null")
    void getEntityClass() {
        assertNull(manager.getEntityClass());
    }

    @Test
    @DisplayName("should return null")
    void getDtoClass() {
        assertNull(manager.getDtoClass());
    }



    @Test
    void dtoToEntity() {
        assertNull(manager.dtoToEntity(null));
    }

    @Test
    void entityToDto() {
        assertNull(manager.entityToDto(null));
    }

   /* @Test
    void update(){
        when(dao.update(any())).thenReturn(true);
        assertTrue(manager.update(obj));
    }*/


}