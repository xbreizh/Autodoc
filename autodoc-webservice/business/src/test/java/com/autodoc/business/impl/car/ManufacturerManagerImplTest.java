package com.autodoc.business.impl.car;

import com.autodoc.business.contract.car.ManufacturerManager;
import com.autodoc.dao.impl.car.ManufacturerDaoImpl;
import com.autodoc.model.models.car.Manufacturer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
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

  /*  @Test
    void getByName() {
        String name = "bob";
        Manufacturer manufacturer1 = new Manufacturer(name);
        when(manufacturerDao.getByName(anyString())).thenReturn(manufacturer1);
        ManufacturerDTO manufacturer = (ManufacturerDTO) manufacturerManager.entityToDto(manufacturer1);
        assertAll(
                () -> assertEquals(name, manufacturerManager.getByName(name).getName()),
                () -> assertEquals(name, manufacturerManager.getByName(name).getName())
        );
    }*/


    @Test
    @Disabled
    void resetException() {
        fail();
    }


  /*  @Test
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
*/

    @Test
    @DisplayName("return false when dao returns false")
    void deleteById() throws Exception {
        int id = 3;
        when(manufacturerDao.getById(id)).thenReturn(false);
        assertFalse(manufacturerManager.deleteById(id));
    }

    @Test
    @DisplayName("return true when dao returns true")
    void deleteById1() throws Exception {
        int id = 3;
        when(manufacturerDao.deleteById(anyInt())).thenReturn(true);
        when(manufacturerDao.getById(id)).thenReturn(new Manufacturer());
        assertTrue(manufacturerManager.deleteById(id));
    }


}
