package com.autodoc.controllers.helper;

import org.apache.log4j.BasicConfigurator;
import org.junit.jupiter.api.BeforeEach;

class GsonConverterTest {
    GsonConverter converter;

    @BeforeEach
    void init() {
        BasicConfigurator.configure();
        converter = new GsonConverter();
    }


}
