package com.autodoc.impl;

import com.autodoc.contract.PieceTypeService;
import com.autodoc.model.dtos.pieces.PieceTypeDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
class PieceTypeServiceImplTest {

    String name = "sdsdsd";
    private PieceTypeService service;
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NjY5OTM1NSwiaWF0IjoxNTc2NjgxMzU1fQ.xxKBPNKwLwJvqpXM920A3sd8i9hf6dRxzlSTQrUDVNDiL2DFxilI5CvrI_x5EK1AZ-Y83VS75BQ2Jw-DGAQEiQ";
    private PieceTypeDTO dto;

    @BeforeEach
    void init() {
        service = new PieceTypeServiceImpl();
        dto = new PieceTypeDTO();
        dto.setName(name);
    }


    @Test
    void getObjectClass() {
    }

    @Test
    void getByid() {
        LOGGER.info("pieceType: " + service.getById(token, 1));
        assertNotNull(service.getById(token, 1));
    }

    @Test
    void getByid1() {
        LOGGER.info("pieceType: " + service.getById(token, 12222));
        assertNull(service.getById(token, 1222));
    }

    @Test
    void update() {
        int id = 1;
        PieceTypeDTO pieceType = (PieceTypeDTO) service.getById(token, id);
        String name = "MOLOK";
        pieceType.setName(name);
        LOGGER.info(pieceType);
        service.update(token, pieceType);
        assertEquals(name, ((PieceTypeDTO) service.getById(token, id)).getName());
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