/*
package com.autodoc.dao.impl.global;

import com.autodoc.dao.filler.Filler;
import com.autodoc.dao.filler.Remover;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.car.Car;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
//@Sql(scripts = "classpath:resetDb_scripts/resetDbCar.sql")
@Transactional
class AbstractHibernateDaoTest {
    private static final Logger LOGGER = Logger.getLogger(AbstractHibernateDaoTest.class);
    @Inject
    GenericHibernateDao genericHibernateDao;

    private Remover remover;
    private Filler filler;

    @BeforeEach
    void init() throws Exception {
        remover.remover();

    }

    @Test
    void setClazz() {
        genericHibernateDao.setClazz(Car.class);
        assertEquals(Car.class, genericHibernateDao.getClazz());
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



}
*/
