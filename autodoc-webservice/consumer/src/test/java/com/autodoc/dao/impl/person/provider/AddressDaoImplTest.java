/*
package com.autodoc.dao.impl.person.provider;

import com.autodoc.dao.contract.person.provider.AddressDao;
import com.autodoc.dao.contract.person.provider.CountryDao;
import com.autodoc.dao.contract.person.provider.ProviderDao;
import com.autodoc.dao.filler.Filler;
import com.autodoc.model.models.person.provider.Address;
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
class AddressDaoImplTest {
    private static final Logger LOGGER = Logger.getLogger(AddressDaoImplTest.class);

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
        LOGGER.info("here");
    }


    @Test
    void save() {

        assertEquals(1, addressDao.getAll().size());
        Address address = new Address();
        address.setCity("Dundalk");
        // address.setCountry((Country) countryDao.getById(3));
        address.setPostcode("12334");
        // address.setProvider((Provider)providerDao.getById(2) );
        address.setStreetName("Harashov street");
        LOGGER.info(addressDao.create(address));
        LOGGER.info(address);
        assertEquals(2, addressDao.getAll().size());
    }


  */
/*  @Test
    void getById() {
        Country country = new Country("ALASKA");
        countryDao.create(country);
        Country country1 = (Country) countryDao.getAll().get(0);
        assertNotNull(countryDao.getById(country1.getId()));
    }*//*



    @Test
    void getAll() {

        assertEquals(2, countryDao.getAll().size());
    }
}*/
