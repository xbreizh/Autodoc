package com.autodoc.dao.impl.car;

import com.autodoc.dao.contract.person.provider.CountryDao;
import com.autodoc.dao.filler.Filler;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;


@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
//@Sql(scripts = "classpath:resetDb_scripts/resetDbCar.sql")
@Transactional
class CountryDaoImplTest {

    private static final Logger LOGGER = Logger.getLogger(CountryDaoImplTest.class);

    @Inject
    private CountryDao dao;

    @Inject
    private Filler filler;


    @BeforeEach
    void init() throws Exception {
        filler.fill();

    }

    @Test
    void getAll() {
        assertEquals(6, dao.getAll().size());
    }

    @Test
    @DisplayName("should return country if name valid")
    void getByName() {
        String name = "spain";
        assertNotNull(dao.getByName(name));
    }

    @Test
    @DisplayName("should return country if name invalid")
    void getByName1() {
        String name = "span";
        assertNull(dao.getByName(name));
    }

    @Test
    void getSearchFields() throws Exception {
        LOGGER.info(dao.getSearchField());
        assertNotNull(dao.getSearchField());
    }
}