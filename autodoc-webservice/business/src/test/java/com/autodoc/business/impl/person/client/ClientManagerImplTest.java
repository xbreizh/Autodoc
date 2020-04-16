package com.autodoc.business.impl.person.client;


import com.autodoc.business.contract.person.client.ClientManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.dao.impl.person.client.ClientDaoImpl;
import com.autodoc.model.dtos.person.client.ClientDTO;
import com.autodoc.model.models.person.client.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/*@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
@Transactional*/
class ClientManagerImplTest {

    private ClientDaoImpl dao;
    private ClientManager manager;
    private Client obj;
    private ClientDTO dto;
    private int id = 34;
    private String firstName = "John";
    private String lastName = "bonham";
    private String phoneNumber = "038488474747";
    private String firstNameDto = "John";
    private String lastNameDto = "bonham";
    private String phoneNumberDto = "038488474747";

    @BeforeEach
    void init() {
        dao = mock(ClientDaoImpl.class);
        manager = ClientManagerImpl.builder().dao(dao).build();
        obj = Client.builder().id(id).firstName(firstName).lastName(lastName).phoneNumber(phoneNumber).build();
        dto = ClientDTO.builder().id(id).firstName(firstNameDto).lastName(lastNameDto).phoneNumber(phoneNumberDto).build();
    }


    @Test
    @DisplayName("should return obj if existing")
    void getDao() {
        assertEquals(dao, manager.getDao());

    }


    @Test
    @DisplayName("should return exception if missing firstName")
    void checkAllRequiredValuesArePassed1(){
        dto.setFirstName(null);
        assertThrows(InvalidDtoException.class, ()->manager.checkAllRequiredValuesArePassed(dto));
        dto.setFirstName("");
        assertThrows(InvalidDtoException.class, ()->manager.checkAllRequiredValuesArePassed(dto));
    }


    @Test
    @DisplayName("should return exception if missing lastName")
    void checkAllRequiredValuesArePassed2(){
        dto.setLastName(null);
        assertThrows(InvalidDtoException.class, ()->manager.checkAllRequiredValuesArePassed(dto));
        dto.setLastName("");
        assertThrows(InvalidDtoException.class, ()->manager.checkAllRequiredValuesArePassed(dto));
    }

    @Test
    void entityToDto() {
        dto = (ClientDTO) manager.entityToDto(obj);
        assertAll(
                () -> assertEquals(obj.getFirstName().toUpperCase(), dto.getFirstName()),
                () -> assertEquals(obj.getLastName().toUpperCase(), dto.getLastName()),
                () -> assertEquals(obj.getPhoneNumber().toUpperCase(), dto.getPhoneNumber())
        );
    }

    @Test
    void dtoToEntity() {
        obj = (Client) manager.dtoToEntity(dto);
        assertAll(
                () -> assertEquals(dto.getFirstName().toUpperCase(), obj.getFirstName()),
                () -> assertEquals(dto.getLastName().toUpperCase(), obj.getLastName()),
                () -> assertEquals(dto.getPhoneNumber().toUpperCase(), obj.getPhoneNumber())
        );
    }

    @Test
    @DisplayName("should throw an exception if invalid id")
    void checkIfIdIsValid() {
        when(dao.getById(anyInt())).thenReturn(null);
        assertThrows(InvalidDtoException.class, () -> manager.checkIfIdIsValid(3));
    }

    @Test
    @DisplayName("should throw an exception if id =0")
    void checkIfIdIsValid1() {
        assertThrows(InvalidDtoException.class, () -> manager.checkIfIdIsValid(0));
    }

    @Test
    @DisplayName("should not throw an exception if id valid")
    void checkIfIdIsValid2() {
        when(dao.getById(anyInt())).thenReturn(obj);
        assertDoesNotThrow(() -> manager.checkIfIdIsValid(20));
    }

    @Test
    @DisplayName("should return an exception if obj already exists")
    void checkIfDuplicate() {
        List<Client> clients = new ArrayList<>();
        obj.setId(31);
        clients.add(obj);
        when(dao.getByCriteria(anyList())).thenReturn(clients);
        assertThrows(InvalidDtoException.class, () -> manager.checkIfDuplicate(dto));

    }

    @Test
    @DisplayName("should not return an exception if obj doesn't already exists")
    void checkIfDuplicate1() {
        when(dao.getByCriteria(anyList())).thenReturn(new ArrayList());
        assertDoesNotThrow(() -> manager.checkIfDuplicate(dto));

    }

    @Test
    @DisplayName("should not return an exception if obj already exists and has the same id")
    void checkIfDuplicate2() {
        List<Client> clients = new ArrayList<>();
        clients.add(obj);
        when(dao.getByCriteria(anyList())).thenReturn(clients);
        assertDoesNotThrow(() -> manager.checkIfDuplicate(dto));

    }

    @Test
    @DisplayName("should update")
    void transferUpdate() {
        when(dao.getByCriteria(anyList())).thenReturn(new ArrayList());
        when(dao.getById(anyInt())).thenReturn(obj);
        System.out.println(dto);
        obj = (Client) manager.transferUpdate(dto);
        assertAll(
                () -> assertEquals(dto.getFirstName().toUpperCase(), obj.getFirstName()),
                () -> assertEquals(dto.getLastName().toUpperCase(), obj.getLastName()),
                () -> assertEquals(dto.getPhoneNumber().toUpperCase(), obj.getPhoneNumber())
        );
    }

}