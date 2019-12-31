package com.autodoc.impl;

import com.autodoc.contract.TaskService;
import com.autodoc.model.dtos.tasks.TaskDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
class TaskServiceImplTest {

    String name = "BATTERY CHANGE1";
    private TaskService service;
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3Njg1MzEzMywiaWF0IjoxNTc2ODM1MTMzfQ.FTrgIwjlfOHF4ysTXGrsOIHzVWeU9UMVbmNad6KdxrOpgLY0T2XztpCxSBvEyqk6Sy65eFLBlyol13vVXsPRpQ";
    private TaskDTO dto;
    private Class clazz = TaskDTO.class;

    @BeforeEach
    void init() {
        service = new TaskServiceImpl();
        dto = new TaskDTO();
        dto.setName(name);
        dto.setPrice(60);
        dto.setEstimatedTime(123);
        dto.setDescription("BATTERY CHANGE");
        dto.setTemplate("false");
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
        int id = 2;
        TaskDTO dto = (TaskDTO) service.getById(token, id);
        LOGGER.info(dto);
        String name = "MOLsssOK";
        dto.setName(name);
        LOGGER.info(dto);
        service.update(token, dto);
        assertEquals(name.toUpperCase(), ((TaskDTO) service.getById(token, id)).getName());
    }

    @Test
    void update1() {
        int id = 2;
        TaskDTO dto = (TaskDTO) service.getById(token, id);
        LOGGER.info(dto);
        String name = "MOLsssOK";
        dto.setName(name);
        dto.setEstimatedTime(123);
        dto.setDescription("plouf");
        dto.setPrice(2);
        dto.setTemplate("false");
        LOGGER.info(dto);
        service.update(token, dto);
        assertEquals(name.toUpperCase(), ((TaskDTO) service.getById(token, id)).getName());
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
        service.filler();
        Random random = new Random();
        dto.setName("ssssss" + random.nextInt(33));

        assertEquals(201, service.create(token, dto));
    }

    @Test
    @DisplayName("should return 401 when insertion ko")
    void create1() {
        dto.setName(null);
        assertEquals(400, service.create(token, dto));

    }


}