package com.autodoc.impl;

import com.autodoc.contract.ProviderService;
import com.autodoc.model.dtos.person.provider.ProviderDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
class ProviderServiceImplTest {

    String name = "dedede";
    private ProviderService service;
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NjUyNjU1OSwiaWF0IjoxNTc2NTA4NTU5fQ.1jvALQZqzsY23A8jOSbjI9qkkHnTTlog4srFzOH1p0P3SEa2kI2tZ7NpubgmZlCNN_TSdjSG-g0nuhTj5izjOQ";
    private ProviderDTO dto;

    @BeforeEach
    void init() {
        service = new ProviderServiceImpl();
        dto = new ProviderDTO();
        dto.setFirstName("ssssss");
        dto.setLastName(name);
        dto.setCompany("ssss");
        dto.setEmail1("deded@dede.de");
        dto.setPhoneNumber1("029282726256");
    }


    @Test
    void getObjectClass() {
    }

    @Test
    void getByid() {
        System.out.println("employee: " + service.getById(token, 1));
        assertNotNull(service.getById(token, 1));
    }

    @Test
    void getByid1() {
        System.out.println("employee: " + service.getById(token, 12222));
        assertNull(service.getById(token, 1222));
    }

    @Test
    void update() {
        int id = 1;
        ProviderDTO provider = (ProviderDTO) service.getById(token, id);
        String company = "MOLOK";
        provider.setCompany(company);
        System.out.println(provider);
        service.update(token, provider);
        assertEquals(company, ((ProviderDTO) service.getById(token, id)).getCompany());
    }


    @Test
    void getAll() {
        assertNotNull(service.getAll(token));
    }

    @Test
    @DisplayName("should return 201 when insertion ok")
    void create() {
        System.out.println(dto);
        service.filler();
        service.delete(token, 6);
        assertEquals(201, service.create(token, dto));
    }

    @Test
    @DisplayName("should return 401 when insertion ko")
    void create1() {
        dto.setFirstName(null);
        assertEquals(400, service.create(token, dto));

    }


}