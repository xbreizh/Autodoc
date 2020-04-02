package com.autodoc.dao.impl.global;

/*import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)*/
//@Sql(scripts = "classpath:resetDb_scripts/resetDbCar.sql")
/*
@Transactional
class AbstractHibernateDaoTest {
    private static final Logger LOGGER = Logger.getLogger(AbstractHibernateDaoTest.class);
    @Inject
    GenericHibernateDao genericHibernateDao;

    @Inject
    private Filler filler;


    @BeforeEach
    void init() throws Exception {
        filler.fill();

    }

    @Test
    void setClazz() {
        genericHibernateDao.setClazz(Car.class);
        assertEquals(Car.class, genericHibernateDao.getClazz());
    }


    @Test
    void findAll() {
    }

    @Test
    void create() {
        String typo = "STRING";
        String compare = "EQUALs";

        for (SearchType type : SearchType.values()) {
            if (type.name().equals(typo)) {
                for (String[] str : type.getValues()) {
                    if (str[0].equalsIgnoreCase(compare)) {
                        LOGGER.info("found it");
                        LOGGER.info(type + " " + str[0] + " / " + str[1]);
                    }
                }
            }
        }


    }


    @Test
    void isCompare() {


    }

    @Test
    void delete() {
    }

    @Test
    void update() {

    }

    @Test
    void deleteById() {
    }

    @Test
    void getCurrentSession() {
    }
}
*/
