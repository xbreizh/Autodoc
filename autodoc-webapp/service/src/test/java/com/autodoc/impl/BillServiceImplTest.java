package com.autodoc.impl;

import com.autodoc.contract.BillService;
import com.autodoc.model.dtos.SearchDto;
import com.autodoc.model.dtos.bill.BillDTO;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BillServiceImplTest {

    private static final Logger LOGGER = Logger.getLogger(BillServiceImplTest.class);
    private BillService service;
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU4MDAyMzc4MiwiaWF0IjoxNTgwMDA1NzgyfQ.9ynPKFH6LHm-b3HpfllTRSzM-ud4P-8J1SUcPnzq0E33NX9ktPjhgGWlUeYqasCC_uuJpLRFOXj2aIUFjwk5lw";
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
        assertEquals(3, service.getAll(token).size());
    }

    @Test
    void add() {
        BillDTO dto = new BillDTO();
        dto.setRegistration("D12447F");
        dto.setClientId(2);
        dto.setEmployeeId(1);
        dto.setTotal(123);
        dto.setStatus("COMPLETED");
        List<Integer> tasks = new ArrayList<>();
        tasks.add(2);
        dto.setTasks(tasks);
        assertNotNull(service.create(token, dto));

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