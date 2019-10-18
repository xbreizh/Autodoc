package com.autodoc.business.impl.car;

import com.autodoc.business.contract.car.ManufacturerManager;
import com.autodoc.dao.impl.car.ManufacturerDaoImpl;
import com.autodoc.model.dtos.car.ManufacturerDTO;
import com.autodoc.model.models.car.Manufacturer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ManufacturerManagerImplTest {

    private ManufacturerManager manufacturerManager;
    private ManufacturerDaoImpl manufacturerDao;


    @BeforeEach
    void init() {
        manufacturerDao = mock(ManufacturerDaoImpl.class);
        manufacturerManager = new ManufacturerManagerImpl(manufacturerDao);
    }

    @Test
    void getAll() {
        List<Manufacturer> list = new ArrayList<>();
        when(manufacturerDao.getAll()).thenReturn(list);
        assertNotNull(manufacturerManager.getAll());
    }

    @Test
    void getByName() {
        String name = "BOB";
        Manufacturer manufacturer1 = new Manufacturer(name);
        when(manufacturerDao.getByName(anyString())).thenReturn(manufacturer1);
        ManufacturerDTO manufacturer = (ManufacturerDTO) manufacturerManager.entityToDto(manufacturer1);
        assertAll(
                () -> assertEquals(name, manufacturerManager.getByName(name).getName()),
                () -> assertEquals(name, manufacturerManager.getByName(name).getName())
        );
    }
}