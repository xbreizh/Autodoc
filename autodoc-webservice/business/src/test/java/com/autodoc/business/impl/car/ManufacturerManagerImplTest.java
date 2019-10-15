package com.autodoc.business.impl.car;

import com.autodoc.business.contract.car.ManufacturerManager;
import com.autodoc.dao.impl.car.ManufacturerDaoImpl;
import com.autodoc.model.models.car.Manufacturer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        when(manufacturerDao.findAll()).thenReturn(list);
        assertNotNull(manufacturerManager.getAll());
    }

    @Test
    void getByName() {
        Manufacturer manufacturer = new Manufacturer();
        when(manufacturerDao.getByName(anyString())).thenReturn(manufacturer);
        assertEquals(manufacturer, manufacturerManager.getByName("TOP"));
    }
}