package com.autodoc.impl;

import com.autodoc.contract.BillService;
import com.autodoc.model.dtos.SearchDto;
import com.autodoc.model.dtos.bill.BillDTO;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BillServiceImplTest {

    private static final Logger LOGGER = Logger.getLogger(BillServiceImplTest.class);
    private BillService service;
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU4MjA3MTcxMCwiaWF0IjoxNTgyMDUzNzEwfQ.P5MperueqLLR81axHY2g2aRYBNnF3PzCA3rAPAf5DbgoZbpO1HZk89ZZvKd2h-KglTUKZ5skSd8W99Yr1QwvEw";
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
        dto.setDateReparation(new Date().toString());
        dto.setStatus("COMPLETED");
        List<Integer> tasks = new ArrayList<>();
        tasks.add(2);
        dto.setTasks(tasks);
        assertNotNull(service.create(token, dto));

    }


    @Test
    void getByid() {
        BillDTO dto = (BillDTO) service.getById(token, 1);
        LOGGER.info("bill: " + dto);
        assertNotNull(service.getById(token, 1));
    }

    @Test
    void changeDate() throws ParseException {
        try {
            Date myDate = new Date();
            System.out.println(myDate);

            SimpleDateFormat mdyFormat = new SimpleDateFormat("dd-MM-yyyy");

            String mdy = mdyFormat.format(myDate);

            // Results...
            System.out.println(mdy);
            // Parse the Strings back to dates
            // Note, the formats don't "stick" with the Date value
            System.out.println(mdyFormat.parse(mdy));
        } catch (ParseException exp) {
            exp.printStackTrace();
        }

    }


    @Test
    void testDate() throws ParseException {
        Date date = new Date();
        System.out.println(date);

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