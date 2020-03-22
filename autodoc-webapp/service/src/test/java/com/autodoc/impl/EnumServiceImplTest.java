package com.autodoc.impl;

import com.autodoc.contract.EnumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EnumServiceImplTest extends HelperTest {


    private EnumService service;

    @BeforeEach
    void init() {
        service = new EnumServiceImpl();
    }


    @Test
    void getAllRoles() {
        assertAll(
                () -> assertEquals("MECANIC", service.getAll(token, "roles").get(0)),
                () -> assertEquals("MANAGER", service.getAll(token, "roles").get(1)),
                () -> assertEquals("SECRETARY", service.getAll(token, "roles").get(2))
        );

    }

    @Test
    void getAllFuelTypes() {
        assertAll(
                () -> assertEquals("DIESEL", service.getAll(token, "fuelTypes").get(0)),
                () -> assertEquals("PETROL", service.getAll(token, "fuelTypes").get(1)),
                () -> assertEquals("HYBRID", service.getAll(token, "fuelTypes").get(2)),
                () -> assertEquals("GPL", service.getAll(token, "fuelTypes").get(3)),
                () -> assertEquals("ELECTRIC", service.getAll(token, "fuelTypes").get(4))
        );

    }

    @Test
    void getAllGearBoxes() {
        assertAll(
                () -> assertEquals("MANUAL", service.getAll(token, "gearBoxes").get(0)),
                () -> assertEquals("AUTOMATIC", service.getAll(token, "gearBoxes").get(1))
        );

    }

    @Test
    void getAllStatus() {
        assertAll(
                () -> assertEquals("PENDING", service.getAll(token, "status").get(0)),
                () -> assertEquals("COMPLETED", service.getAll(token, "status").get(1)),
                () -> assertEquals("CANCELLED", service.getAll(token, "status").get(2))
        );

    }


    @Test
    void test() {

        String de = "[[MANAGER ]] ";
        de = de.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "");
        assertEquals("MANAGER", de);

    }

}