package com.autodoc.business.impl.person.provider;

import com.autodoc.business.contract.person.provider.ProviderManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.dao.contract.person.provider.ProviderDao;
import com.autodoc.dao.impl.person.provider.ProviderDaoImpl;
import com.autodoc.model.dtos.person.provider.ProviderDTO;
import com.autodoc.model.models.person.provider.Provider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProviderManagerImplTest {


    private ProviderDao dao;
    private ProviderManager manager;
    private Provider obj;
    private ProviderDTO dto;
    private int id = 34;
    private String firstName = "John";
    private String lastName = "bonham";
    private String phoneNumber = "038488474747";
    private String company = "rosario";
    private String email1 = "joijrfr@frtg";
    private String website = "rosario.com";


    @BeforeEach
    void init() {
        dao = mock(ProviderDaoImpl.class);
        manager = ProviderManagerImpl.builder().dao(dao).build();
        obj = Provider.builder().id(id).firstName(firstName).lastName(lastName).phoneNumber(phoneNumber).company(company).email1(email1).website(website).address("3, rue michard").build();
        dto = ProviderDTO.builder().id(2).firstName("firstNameDTO").lastName("lastNameDTO").phoneNumber("phoneNumberDTO").company("companyDTO").email1("email1DTO").website("websiteDTO").build();
    }


    @Test
    @DisplayName("should return obj if existing")
    void getDao() {
        assertEquals(dao, manager.getDao());

    }

    @Test
    void entityToDto() {
        dto = (ProviderDTO) manager.entityToDto(obj);
        assertAll(
                () -> assertEquals(obj.getFirstName().toUpperCase(), dto.getFirstName()),
                () -> assertEquals(obj.getLastName().toUpperCase(), dto.getLastName()),
                () -> assertEquals(obj.getPhoneNumber().toUpperCase(), dto.getPhoneNumber()),
                () -> assertEquals(obj.getEmail1().toUpperCase(), dto.getEmail1()),
                () -> assertEquals(obj.getCompany().toUpperCase(), dto.getCompany()),
                () -> assertEquals(obj.getWebsite().toUpperCase(), dto.getWebsite())
        );
    }

    @Test
    void dtoToEntity() {
        obj = (Provider) manager.dtoToEntity(dto);
        assertAll(
                () -> assertEquals(dto.getFirstName().toUpperCase(), obj.getFirstName()),
                () -> assertEquals(dto.getLastName().toUpperCase(), obj.getLastName()),
                () -> assertEquals(dto.getPhoneNumber().toUpperCase(), obj.getPhoneNumber()),
                () -> assertEquals(dto.getEmail1().toUpperCase(), obj.getEmail1()),
                () -> assertEquals(dto.getCompany().toUpperCase(), obj.getCompany()),
                () -> assertEquals(dto.getWebsite().toUpperCase(), obj.getWebsite())
        );
    }

    @Test
    @DisplayName("should return an exception if obj already exists")
    void checkIfDuplicate(){
        List<Provider> providers = new ArrayList<>();
        providers.add(new Provider());
        when(dao.getByCriteria(anyList())).thenReturn(providers);
        assertThrows(InvalidDtoException.class, ()-> manager.checkIfDuplicate(dto));

    }

    @Test
    @DisplayName("should not return an exception if obj doesn't already exists")
    void checkIfDuplicate1(){
        when(dao.getByCriteria(anyList())).thenReturn(new ArrayList());
        assertDoesNotThrow(()-> manager.checkIfDuplicate(dto));

    }
    @Test
    @DisplayName("should transfert")
    void transferInsert(){
        obj = (Provider) manager.transferInsert(dto);
        assertAll(
                () -> assertEquals(dto.getFirstName().toUpperCase(), obj.getFirstName()),
                () -> assertEquals(dto.getLastName().toUpperCase(), obj.getLastName()),
                () -> assertEquals(dto.getPhoneNumber().toUpperCase(), obj.getPhoneNumber()),
                () -> assertEquals(dto.getEmail1().toUpperCase(), obj.getEmail1()),
                () -> assertEquals(dto.getCompany().toUpperCase(), obj.getCompany()),
                () -> assertEquals(dto.getWebsite().toUpperCase(), obj.getWebsite())
        );

    }

    @Test
    @DisplayName("should update")
    void transferUpdate(){
        obj = (Provider) manager.transferUpdate(dto);
        assertAll(
                () -> assertEquals(dto.getFirstName().toUpperCase(), obj.getFirstName()),
                () -> assertEquals(dto.getLastName().toUpperCase(), obj.getLastName()),
                () -> assertEquals(dto.getPhoneNumber().toUpperCase(), obj.getPhoneNumber()),
                () -> assertEquals(dto.getEmail1().toUpperCase(), obj.getEmail1()),
                () -> assertEquals(dto.getCompany().toUpperCase(), obj.getCompany()),
                () -> assertEquals(dto.getWebsite().toUpperCase(), obj.getWebsite())
        );

    }
}
