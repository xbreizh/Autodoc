package com.autodoc.impl;

import com.autodoc.contract.PieceService;
import com.autodoc.model.dtos.pieces.PieceDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
class PieceServiceImplTest {

    String name = "sdsdsd";
    private PieceService service;
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3Njc4MjczMywiaWF0IjoxNTc2NzY0NzMzfQ.hqdGifYIikzrFlW9pv8fgUt9nJUjq5jxYtYWQ2QxcMwVMc83BQ-OeBc9X4CyHPTa5sut60MrvvSCpBCD2Rh-9w";
    private PieceDTO dto;

    @BeforeEach
    void init() {
        service = new PieceServiceImpl();
        dto = new PieceDTO();
        dto.setName(name);
    }


    @Test
    void getObjectClass() {
    }

    @Test
    void getByid() {
        LOGGER.info("piece: " + service.getById(token, 1));
        assertNotNull(service.getById(token, 3));
    }

    @Test
    void getByid1() {
        LOGGER.info("piece: " + service.getById(token, 12222));
        assertNull(service.getById(token, 1222));
    }

    @Test
    void update() {
        int id = 3;
        PieceDTO piece = (PieceDTO) service.getById(token, id);
        String name = "MOLOK";
        piece.setName(name);
        LOGGER.info(piece);
        service.update(token, piece);
        assertEquals(name, ((PieceDTO) service.getById(token, id)).getName());
    }


    @Test
    void getAll() {
        assertNotNull(service.getAll(token));
    }

    @Test
    @DisplayName("should return 201 when insertion ok")
    void create() {
        LOGGER.info(dto);
        Random random = new Random();
        dto.setName("paltoquet");
        dto.setBrand(name);
        dto.setBuyingPrice(12);
        dto.setSellPrice(24);
        dto.setPieceTypeId(2);
        dto.setCarModelId(1);
       // service.filler();
        assertEquals(201, service.create(token, dto));
    }

    @Test
    @DisplayName("should return 401 when insertion ko")
    void create1() {
        dto.setName(null);
        assertEquals(400, service.create(token, dto));

    }


}