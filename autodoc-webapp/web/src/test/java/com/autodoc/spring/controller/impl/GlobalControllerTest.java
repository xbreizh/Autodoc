package com.autodoc.spring.controller.impl;


import com.autodoc.helper.Helper;
import com.autodoc.helper.HelperImpl;
import com.autodoc.model.models.bill.Bill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class GlobalControllerTest {

    Helper helper;

    @BeforeEach
    void init() {
        helper = new HelperImpl();
    }


    @Test
    void saveAsPdf() throws IOException {

        helper.generatePDFFromHTML(new Bill());

    }

}
