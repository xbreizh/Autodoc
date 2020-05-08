package com.autodoc.business.impl.car;


import com.autodoc.business.contract.car.CarModelManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.dao.contract.car.CarModelDao;
import com.autodoc.dao.impl.car.CarModelDaoImpl;
import com.autodoc.model.dtos.car.CarModelDTO;
import com.autodoc.model.models.car.CarModel;
import org.apache.log4j.BasicConfigurator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/*@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
//@Sql(scripts = "classpath:resetDb_scripts/resetDbCar.sql")
@Transactional*/
class CarModelManagerImplTest {

    private CarModelManager manager;
    private CarModelDao dao;
    private final String name = "Bob";
    private CarModel obj;
    private CarModelDTO dto;
    private final int id = 22;
    private final String nameDto = "niamo";
    private final String description = "descriere";
    private final String engine = "12324AS";


    @BeforeEach
    void init() {
        BasicConfigurator.configure();
        dao = mock(CarModelDaoImpl.class);
        manager = CarModelManagerImpl.builder().dao(dao).build();
        obj = CarModel.builder().id(id).name(name).build();
        dto = CarModelDTO.builder().name(nameDto).description(description).fuelType("PETROL").engine(engine).build();
    }


    @Test
    @DisplayName("should return obj if existing")
    void getDao() {
        assertEquals(dao, manager.getDao());

    }

    @Test
    @DisplayName("should convert dto into entity")
    void dtoToEntity() {
        obj = (CarModel) manager.dtoToEntity(dto);
        assertAll(
                () -> assertEquals(dto.getName().toUpperCase(), obj.getName()),
                () -> assertEquals(dto.getDescription().toUpperCase(), obj.getDescription()),
                () -> assertEquals(dto.getFuelType(), obj.getFuelType().toString()),
                () -> assertEquals(dto.getEngine().toUpperCase(), obj.getEngine()),
                () -> assertEquals(dto.getId(), obj.getId())
        );

    }


    @Test
    @DisplayName("should return null if entity is null")
    void dtoToEntity1() {
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
    @DisplayName("should return null if name is null")
    void getByName1() {
        assertNull(manager.getByName(null));

    }

    @Test
    @DisplayName("should return null if name is empty")
    void getByName2() {
        assertNull(manager.getByName(""));

    }


    @Test
    @DisplayName("should throw an exception if name null")
    void checkIfDuplicate1() {
        dto.setName(null);
        assertThrows(InvalidDtoException.class, () -> manager.checkIfDuplicate(dto));
    }

    @Test
    @DisplayName("should throw an exception if name empty")
    void checkIfDuplicate4() {
        dto.setName("");
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
