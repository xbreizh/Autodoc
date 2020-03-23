package com.autodoc.impl;

import com.autodoc.contract.ProviderService;
import com.autodoc.model.dtos.person.provider.ProviderDTO;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
class ProviderServiceImplTest extends HelperTest {

    String name = "sdsdsd";
    private ProviderService service;

    private ProviderDTO dto;
    private Class clazz = ProviderDTO.class;
    private static final Logger LOGGER = Logger.getLogger(ProviderServiceImplTest.class);

    @BeforeEach
    void init() {

        service = new ProviderServiceImpl();
        dto = new ProviderDTO();
        dto.setFirstName("ssssss");
        dto.setLastName(name);
        dto.setCompany("ssss");
        dto.setEmail1("deded@dede.de");
        dto.setPhoneNumber("029282726256");
    }


    @Test
    void getObjectClass() {
    }

    @Test
    void getByid() {
        LOGGER.info("employee: " + service.getById(token, 1));
        assertNotNull(service.getById(token, 1));
    }

    @Test
    void getByid1() {
        LOGGER.info("employee: " + service.getById(token, 12222));
        assertNull(service.getById(token, 1222));
    }

    @Test
    void update() {
        int id = 1;
        ProviderDTO provider = (ProviderDTO) service.getById(token, id);
        String company = "MOLOK";
        provider.setCompany(company);
        LOGGER.info(provider);
        service.update(token, provider);
        assertEquals(company, ((ProviderDTO) service.getById(token, id)).getCompany());
    }


    @Test
    void getAll() {
        assertNotNull(service.getAll(token));
        assertEquals(clazz, service.getAll(token).get(0).getClass());
    }

    @Test
    @DisplayName("should return 201 when insertion ok")
    void create() {
        LOGGER.info(dto);
        assertEquals(201, service.create(token, dto));
    }

    @Test
    @DisplayName("should return 401 when insertion ko")
    void create1() {
        dto.setFirstName(null);
        assertEquals(401, service.create(token, dto));

    }


}