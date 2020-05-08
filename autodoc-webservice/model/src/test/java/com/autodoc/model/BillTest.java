package com.autodoc.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BillTest {


    @Test
    void cleanup(){
        String date = "02-02-2018 00:00:00";
        String newDate=date.replaceAll(" .+$", "");



        assertEquals("02-02-2018", newDate);


    }

}