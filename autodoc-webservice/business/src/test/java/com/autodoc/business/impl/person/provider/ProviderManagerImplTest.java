/*
package com.autodoc.business.impl.person.provider;

import com.autodoc.business.contract.person.provider.AddressManager;
import com.autodoc.dao.contract.person.provider.CountryDao;
import com.autodoc.dao.contract.person.provider.ProviderDao;
import com.autodoc.dao.impl.person.provider.AddressDaoImpl;
import com.autodoc.model.models.car.Manufacturer;
import com.autodoc.model.models.person.provider.Address;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class ProviderManagerImplTest {


    private AddressManager addressManager;
    private AddressDaoImpl addressDao;
    private CountryDao countryDao;
    private ProviderDao providerDao;


 */
/*   @BeforeEach
    void init() {
        addressDao = mock(AddressDaoImpl.class);
        providerDao = mock(ProviderDaoImpl.class);
        countryDao = mock(CountryDaoImpl.class);
        addressManager = new AddressManagerImpl(addressDao, countryDao, providerDao);
    }*//*


    @Test
    void getAll() {
        List<Manufacturer> list = new ArrayList<>();
        when(addressDao.getAll()).thenReturn(list);
        assertNotNull(addressManager.getAll());
    }


    @Test
    void resetException() {
    }

  */
/*  @Test
    void save() throws Exception {
        Provider provider = new Provider();
        when(addressDao.create(any(Address.class))).thenReturn(id);
        when(countryDao.getByName(anyString())).thenReturn(country);
        assertEquals(Integer.toString(id), addressManager.save(addressDTO));
    }*//*


    @Test
    void checkDataInsert() {
    }

    @Test
    void checkDataUpdate() {
    }

    @Test
    void getById() throws Exception {
        int id = 9;
        when(addressDao.getById(anyInt())).thenReturn(new Address());
        assertNotNull(addressManager.getById(id));
    }


    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }


    @Test
    void entityToDto() {
    }

    @Test
    void dtoToEntity() {
    }
}
*/
