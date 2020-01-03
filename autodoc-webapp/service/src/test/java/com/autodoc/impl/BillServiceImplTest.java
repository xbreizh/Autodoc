package com.autodoc.impl;

import com.autodoc.contract.BillService;
import com.autodoc.model.dtos.SearchDto;
import com.autodoc.model.dtos.bill.BillDTO;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BillServiceImplTest {

    private static final Logger LOGGER = Logger.getLogger(BillServiceImplTest.class);
    private BillService service;
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3ODA5ODQ0MiwiaWF0IjoxNTc4MDgwNDQyfQ.0un7FLzvqtfdFCBhlXM0TJGTK1w6BwF91TeaxHO9H2bgtg7M4ooJ1Mo6L7_5mXxd4Ge_zlS9m92tU_CvbNkFmg";
    private Class clazz = BillDTO.class;

    @BeforeEach
    void init() {
        service = new BillServiceImpl();
    }


    @Test
    void getObjectClass() {
    }

    @Test
    void getAll() {
        assertNotNull(service.getAll(token));
        assertEquals(clazz, service.getAll(token).get(0).getClass());
    }


    @Test
    void getByid() {
        LOGGER.info("employee: " + service.getById(token, 1));
        assertNotNull(service.getById(token, 1));
    }


    @Test
    void getByCriteria() {
        SearchDto searchDto = new SearchDto();
        searchDto.setFieldName("car.registration");
        searchDto.setCompare("contains");
        searchDto.setValue("D12447F");
        LOGGER.info("employee: " + service.getByCriteria(token, searchDto));
        // assertNotNull(service.getById(token, 1));
    }
}