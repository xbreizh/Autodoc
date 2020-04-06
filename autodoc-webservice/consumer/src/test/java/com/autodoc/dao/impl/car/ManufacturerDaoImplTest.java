package com.autodoc.dao.impl.car;

import com.autodoc.dao.contract.car.ManufacturerDao;
import com.autodoc.dao.filler.Filler;
import com.autodoc.model.models.car.Manufacturer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
@Transactional
class ManufacturerDaoImplTest {

    @Inject
    ManufacturerDao dao;

    @Inject
    private Filler filler;


    @BeforeEach
    void init() throws Exception {
        filler.fill();

    }

    @Test
    @DisplayName("should return object from name")
    void getByName() {
        Manufacturer manufacturer = (Manufacturer) dao.getAll().get(0);
        String name = manufacturer.getName().toLowerCase();
        assertEquals(manufacturer, dao.getByName(name));
    }

    @Test
    @DisplayName("should return null if invalid name")
    void getByName1() {
        assertNull(dao.getByName("coremon"));
    }


    @Test
    @DisplayName("should return searchFields from Model")
    void getSearchField() {
        assertEquals(Manufacturer.getSearchField(), dao.getSearchField());
    }
}
