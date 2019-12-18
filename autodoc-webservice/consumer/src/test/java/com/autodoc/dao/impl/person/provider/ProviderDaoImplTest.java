package com.autodoc.dao.impl.person.provider;

import com.autodoc.dao.contract.person.provider.AddressDao;
import com.autodoc.dao.contract.person.provider.CountryDao;
import com.autodoc.dao.contract.person.provider.ProviderDao;
import com.autodoc.dao.filler.Filler;
import com.autodoc.model.models.person.provider.Country;
import com.autodoc.model.models.person.provider.Provider;
import org.junit.jupiter.api.BeforeEach;
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
class ProviderDaoImplTest {


    @Inject
    private AddressDao addressDao;
    @Inject
    private CountryDao countryDao;
    @Inject
    private ProviderDao providerDao;

    @Inject
    private Filler filler;


    @BeforeEach
    void init() throws Exception {
        filler.fill();
        System.out.println("here");
    }


    @Test
    void save() {

        assertEquals(2, providerDao.getAll().size());
        Provider provider = new Provider();
        provider.setFirstName("JAmes");
        provider.setLastName("bohiu");
        provider.setCompany("nuliku");
        provider.setEmail1("dedede@Deded.de");
        providerDao.create(provider);
        assertEquals(3, providerDao.getAll().size());
    }


    @Test
    void getById() {
        Country country = new Country("ALASKA");
        countryDao.create(country);
        Country country1 = (Country) countryDao.getAll().get(0);
        assertNotNull(countryDao.getById(country1.getId()));
    }


    @Test
    void getAll() {

        assertEquals(2, countryDao.getAll().size());
    }
}