package com.autodoc.business.impl.car;

import com.autodoc.business.contract.car.CarManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.dao.contract.car.CarDao;
import com.autodoc.dao.contract.car.CarModelDao;
import com.autodoc.dao.contract.person.client.ClientDao;
import com.autodoc.dao.impl.car.CarDaoImpl;
import com.autodoc.model.dtos.car.CarDTO;
import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.person.client.Client;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CarManagerImplTest {

    private static final Logger LOGGER = Logger.getLogger(CarManagerImplTest.class);

    private CarManager manager;
    private CarDao dao;
    private ClientDao clientDao;
    private CarModelDao carModelDao;
    private Car obj;
    private CarDTO dto;
    private int id = 323;
    private String registration = "abc23";
    private Client client;
    private CarModel carModel;
    private List<Bill> bills;
    private int clienttId = 3;
    private int carModelId = 2;


    @BeforeEach
    void init() {
        dao = mock(CarDaoImpl.class);
        carModelDao = mock(CarModelDao.class);
        clientDao = mock(ClientDao.class);
        manager = CarManagerImpl.builder().carModelDao(carModelDao).clientDao(clientDao).dao(dao).build();
        client = Client.builder().firstName("jean").lastName("rocamor").phoneNumber("abc1233").build();
        carModel = CarModel.builder().id(id).name("niamo").build();
        obj = Car.builder().id(id).registration(registration).client(client).carModel(carModel).bills(bills).build();
        dto = CarDTO.builder().id(id).registration(registration).clientId(clienttId).carModelId(carModelId).build();
    }


    @Test
    @DisplayName("should return obj if existing")
    void getDao() {
        assertEquals(dao, manager.getDao());

    }

    @Test
    @DisplayName("should return null")
    void entityToDto() {
        assertNull(manager.entityToDto(null));
    }

    @Test
    @DisplayName("should convert entity")
    void entityToDto1() {
        dto = (CarDTO) manager.entityToDto(obj);
        assertAll(
                () -> assertEquals(obj.getRegistration().toUpperCase(), dto.getRegistration()),
                () -> assertEquals(obj.getCarModel().getId(), dto.getCarModelId()),
                () -> assertEquals(obj.getClient().getId(), dto.getClientId())
        );
    }

    @Test
    @DisplayName("should convert into entity")
    void dtoToEntity() {
        when(clientDao.getById(anyInt())).thenReturn(client);
        when(carModelDao.getById(anyInt())).thenReturn(carModel);
        obj = (Car) manager.dtoToEntity(dto);
        assertAll(
                () -> assertEquals(dto.getRegistration().toUpperCase(), obj.getRegistration()),
                () -> assertEquals(dto.getCarModelId(), obj.getCarModel().getId()),
                () -> assertEquals(dto.getClientId(), obj.getClient().getId())
        );
    }

    @Test
    @DisplayName("should return false if car not in db ")
    void checkRegistrationNotInUse() {
        when(dao.getCarByRegistration(anyString())).thenReturn(null);
        assertFalse(manager.checkRegistrationNotInUse(id, registration));
    }

    @Test
    @DisplayName("should return false if car  in db and id different ")
    void checkRegistrationNotInUse1() {
        when(dao.getCarByRegistration(anyString())).thenReturn(obj);
        assertFalse(manager.checkRegistrationNotInUse(234, registration));
    }

    @Test
    @DisplayName("should return true if car  in db and id same ")
    void checkRegistrationNotInUse2() {
        when(dao.getCarByRegistration(anyString())).thenReturn(obj);
        assertTrue(manager.checkRegistrationNotInUse(id, registration));
    }

    @Test
    @DisplayName("should return true if existing car")
    void checkIfExistingCar() {
        when(dao.getCarByRegistration(anyString())).thenReturn(obj);
        assertThrows(InvalidDtoException.class, () -> manager.checkIfExistingCar(registration));

    }

    @Test
    @DisplayName("should return true if existing car")
    void checkIfExistingCar1() {
        when(dao.getCarByRegistration(anyString())).thenReturn(null);
        assertDoesNotThrow(() -> manager.checkIfExistingCar(registration));

    }


    @Test
    @DisplayName("should return null if registration not found")
    void getByRegistration() {
        when(dao.getCarByRegistration(anyString())).thenReturn(null);
        assertNull(manager.getByRegistration("blop"));
    }


    @Test
    @DisplayName("should return car if registration found")
    void getByRegistration1() {
        when(dao.getCarByRegistration(anyString())).thenReturn(obj);
        assertNotNull(manager.getByRegistration("dede"));
    }

    @Test
    @DisplayName("should throw an exception if carmodel doesn't exist")
    void checkCarModelExist() {
        when(carModelDao.getById(anyInt())).thenReturn(null);
        assertThrows(EntityNotFoundException.class, () -> manager.checkCarModelExist(3));
    }

    @Test
    @DisplayName("should not throw an exception if carmodel exists")
    void checkCarModelExist1() {
        when(carModelDao.getById(anyInt())).thenReturn(carModel);
        assertDoesNotThrow(() -> manager.checkCarModelExist(3));
    }

    @Test
    @DisplayName("should throw an exception if client doesn't exist")
    void checkClientExist() {
        when(clientDao.getById(anyInt())).thenReturn(null);
        assertThrows(EntityNotFoundException.class, () -> manager.checkClientExist(3));
    }

    @Test
    @DisplayName("should not throw an exception if client exists")
    void checkClientExist1() {
        when(clientDao.getById(anyInt())).thenReturn(client);
        assertDoesNotThrow(() -> manager.checkClientExist(3));
    }

    @Test
    @DisplayName("should update the client")
    void updateClient() {
        when(clientDao.getById(anyInt())).thenReturn(client);
        when(dao.getById(anyInt())).thenReturn(obj);
        assertEquals(client.getId(), manager.updateClient(2, 4).getClientId());
    }


    @Test
    @DisplayName("should throw an exception if no client found")
    void updateClient1() {
        when(clientDao.getById(anyInt())).thenReturn(null);
        assertThrows(InvalidDtoException.class, () -> manager.updateClient(2, 3));
    }


    @Test
    @DisplayName("should insert car")
    void transferInsert() {
        when(clientDao.getById(anyInt())).thenReturn(client);
        when(carModelDao.getById(anyInt())).thenReturn(carModel);
        obj = (Car) manager.transferInsert(dto);
        assertAll(
                () -> assertEquals(dto.getRegistration().toUpperCase(), obj.getRegistration()),
                () -> assertEquals(dto.getCarModelId(), obj.getCarModel().getId()),
                () -> assertEquals(dto.getClientId(), obj.getClient().getId())
        );

    }

    @Test
    @DisplayName("should update the car")
    void transferUpdate() {
        dto.setClientId(client.getId());
        dto.setCarModelId(carModel.getId());
        when(clientDao.getById(anyInt())).thenReturn(client);
        when(carModelDao.getById(anyInt())).thenReturn(carModel);
        when(dao.getById(anyInt())).thenReturn(obj);
        obj = manager.transferUpdate(dto);
        System.out.println(dto);
        assertAll(
                () -> assertEquals(dto.getRegistration().toUpperCase(), obj.getRegistration()),
                () -> assertEquals(dto.getCarModelId(), obj.getCarModel().getId()),
                () -> assertEquals(dto.getClientId(), obj.getClient().getId())
        );

    }

    @Test
    @DisplayName("should throw an exception if no car id provided and registration is empty")
    void transferUpdate1() {
        dto.setId(0);
        dto.setRegistration("");
        assertThrows(InvalidDtoException.class, () -> manager.transferUpdate(dto));

    }

    @Test
    @DisplayName("should throw an exception if no car id provided and registration is null")
    void transferUpdate5() {
        dto.setId(0);
        dto.setRegistration(null);
        assertThrows(InvalidDtoException.class, () -> manager.transferUpdate(dto));

    }

    @Test
    @DisplayName("should throw an exception if no clientId and carid are not  provided")
    void transferUpdate2() {
        dto.setCarModelId(0);
        dto.setClientId(0);
        assertThrows(InvalidDtoException.class, () -> manager.transferUpdate(dto));

    }

    @Test
    @DisplayName("should throw an exception if no valid car id provided")
    void transferUpdate3() {
        when(dao.getById(anyInt())).thenReturn(null);
        assertThrows(InvalidDtoException.class, () -> manager.transferUpdate(dto));

    }

    @Test
    @DisplayName("should throw an exception if car id = 0 and invalid registration provided")
    void transferUpdate4() {
        dto.setId(0);
        when(dao.getCarByRegistration(anyString())).thenReturn(null);
        assertThrows(InvalidDtoException.class, () -> manager.transferUpdate(dto));

    }

    @Test
    @DisplayName("should update the car when carModelId = 0")
    void transferUpdate6() {
        client.setId(32);
        dto.setClientId(32);
        dto.setCarModelId(0);
        when(clientDao.getById(anyInt())).thenReturn(client);
        when(dao.getById(anyInt())).thenReturn(obj);
        obj = manager.transferUpdate(dto);
        System.out.println(dto);
        assertAll(
                () -> assertEquals(dto.getRegistration().toUpperCase(), obj.getRegistration()),
                () -> assertEquals(dto.getCarModelId(), obj.getCarModel().getId()),
                () -> assertEquals(dto.getClientId(), obj.getClient().getId())
        );

    }

    @Test
    @DisplayName("should update the car when clientId = 0")
    void transferUpdate7() {
        carModel.setId(32);
        dto.setClientId(0);
        dto.setCarModelId(32);
        when(carModelDao.getById(anyInt())).thenReturn(carModel);
        when(dao.getById(anyInt())).thenReturn(obj);
        obj = manager.transferUpdate(dto);
        assertAll(
                () -> assertEquals(dto.getRegistration().toUpperCase(), obj.getRegistration()),
                () -> assertEquals(dto.getCarModelId(), obj.getCarModel().getId()),
                () -> assertEquals(dto.getClientId(), obj.getClient().getId())
        );

    }

    @Test
    @DisplayName("should throw an exception when carModelId = 0 and clientId = 0")
    void transferUpdate0() {
        dto.setClientId(0);
        dto.setCarModelId(0);
        when(dao.getById(anyInt())).thenReturn(obj);

        assertThrows(InvalidDtoException.class, () -> manager.transferUpdate(dto));


    }


    @Test
    @DisplayName("should throw an error if car id not in db")
    void checkIfCarInDb() {
        when(dao.getById(anyInt())).thenReturn(null);
        assertThrows(InvalidDtoException.class, () -> manager.checkIfCarInDb(3, "dedsd"));
    }

    @Test
    @DisplayName("should throw an error if car registration not in db")
    void checkIfCarInDb1() {
        when(dao.getCarByRegistration(anyString())).thenReturn(null);
        assertThrows(InvalidDtoException.class, () -> manager.checkIfCarInDb(3, "dedsd"));
    }

    @Test
    @DisplayName("should not throw an error if car registration in db")
    void checkIfCarInDb2() {
        when(dao.getCarByRegistration(anyString())).thenReturn(obj);
        assertDoesNotThrow(() -> manager.checkIfCarInDb(0, "dedsd"));
    }

    @Test
    @DisplayName("should not throw an error if car id in db")
    void checkIfCarInDb3() {
        when(dao.getById(anyInt())).thenReturn(obj);
        assertDoesNotThrow(() -> manager.checkIfCarInDb(3, "dedsd"));
    }

    @Test
    @DisplayName("should not throw an error if car id in db")
    void checkIfCarInDb4() {
        when(dao.getById(anyInt())).thenReturn(obj);
        assertThrows(InvalidDtoException.class, () -> manager.checkIfCarInDb(0, ""));
    }


}
