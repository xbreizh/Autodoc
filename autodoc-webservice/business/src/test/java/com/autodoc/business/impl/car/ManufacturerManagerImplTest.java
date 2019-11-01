package com.autodoc.business.impl.car;

import com.autodoc.business.contract.car.ManufacturerManager;
import com.autodoc.dao.impl.car.ManufacturerDaoImpl;
import com.autodoc.model.dtos.car.ManufacturerDTO;
import com.autodoc.model.models.car.Manufacturer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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


    @Test
    void resetException() {
    }

    @Test
    void save() throws Exception {
       /* String name = "zop";
        ManufacturerDTO manufacturer = new ManufacturerDTO(name);
        when(manufacturerDao.getByName(anyString())).thenReturn(null);
        when(manufacturerDao.create(any(Manufacturer.class))).thenReturn("");
        assertEquals(manufacturer.getClass().getSimpleName() + " added", manufacturerManager.save(manufacturer));*/
    }


    @Test
    void getById() throws Exception {
        String name = "Paul";
        Manufacturer manufacturer = new Manufacturer(name);
        when(manufacturerDao.getById(anyInt())).thenReturn(manufacturer);
        ManufacturerDTO dto = (ManufacturerDTO) manufacturerManager.getById(3);
        assertAll(
                () -> assertEquals(ManufacturerDTO.class.getSimpleName(), manufacturerManager.getById(3).getClass().getSimpleName()),
                () -> assertEquals(name, dto.getName())
        );

    }

    @Test
    void testGetAll() {
        List<Manufacturer> list = new ArrayList<>();
        Manufacturer manufacturer = new Manufacturer("One");
        Manufacturer manufacturer1 = new Manufacturer("Two");
        list.add(manufacturer);
        list.add(manufacturer1);
        when(manufacturerDao.getAll()).thenReturn(list);
        assertEquals(2, manufacturerManager.getAll().size());
    }

    @Test
    void update() throws Exception {
        int id = 3;
        String name = "John";
        ManufacturerDTO dto = new ManufacturerDTO(name);
        when(manufacturerDao.update(dto)).thenReturn("");
        assertEquals(name, dto.getName());

    }

    @Test
    void delete() {

    }

    @Test
    @DisplayName("return empty string when id not found")
    void deleteById() {
        int id = 3;
        Manufacturer manufacturer1 = new Manufacturer("Two");
        when(manufacturerDao.getById(id)).thenReturn(manufacturer1);
        assertEquals("", manufacturerManager.deleteById(id));
    }

    @Test
    @DisplayName("return not found when id not found")
    void deleteById1() {
        int id = 3;
        when(manufacturerDao.getById(id)).thenReturn(null);
        assertEquals("not Found", manufacturerManager.deleteById(id));
    }

    @Test
    void entityToDto() {
    }

    @Test
    void dtoToEntity() throws Exception {
        String name = "Paul";
        ManufacturerDTO dto = new ManufacturerDTO(name);
        Manufacturer manufacturer = (Manufacturer) manufacturerManager.dtoToEntity(dto);
        assertEquals(name, manufacturer.getName());
    }

    @Test
    void testGetByName() {
    }

    @Test
    void testCheckData() {
    }
}