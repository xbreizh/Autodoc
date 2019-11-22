package com.autodoc.dao.impl.person.provider;

import com.autodoc.dao.contract.person.provider.CountryDao;
import com.autodoc.model.models.person.provider.Country;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
//@Sql(scripts = "classpath:resetDb_scripts/resetDbCar.sql")
@Transactional
class CountryDaoImplTest {


    @Inject
    private CountryDao countryDao;


    @Test
    void save() {
        Country country = new Country("ALASKA");
        countryDao.create(country);
        assertEquals(1, countryDao.getAll().size());
    }


    @Test
    void getById() {
        Country country = new Country("ALASKA");
        countryDao.create(country);
        Country country1 = (Country) countryDao.getAll().get(0);
        assertNotNull(countryDao.getById(country1.getId()));
    }
}