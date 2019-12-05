package com.autodoc.business.impl.person.provider;

import com.autodoc.business.contract.person.provider.AddressManager;
import com.autodoc.dao.contract.person.provider.CountryDao;
import com.autodoc.dao.contract.person.provider.ProviderDao;
import com.autodoc.dao.impl.person.provider.AddressDaoImpl;
import com.autodoc.dao.impl.person.provider.CountryDaoImpl;
import com.autodoc.dao.impl.person.provider.ProviderDaoImpl;
import com.autodoc.model.dtos.person.provider.AddressDTO;
import com.autodoc.model.models.car.Manufacturer;
import com.autodoc.model.models.person.provider.Address;
import com.autodoc.model.models.person.provider.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AddressManagerImplTest {


    private AddressManager addressManager;
    private AddressDaoImpl addressDao;
    private CountryDao countryDao;
    private ProviderDao providerDao;


    @BeforeEach
    void init() {
        addressDao = mock(AddressDaoImpl.class);
        providerDao = mock(ProviderDaoImpl.class);
        countryDao = mock(CountryDaoImpl.class);
        addressManager = new AddressManagerImpl(addressDao, countryDao, providerDao);
    }

    @Test
    void getAll() {
        List<Manufacturer> list = new ArrayList<>();
        when(addressDao.getAll()).thenReturn(list);
        assertNotNull(addressManager.getAll());
    }


  /*  @Test
    void searchByCriteria() throws Exception {
        List<SearchDTO> searchList = new ArrayList<>();
        SearchDTO dto1 = new SearchDTO("name", "equals", "belgium");
        searchList.add(dto1);
        when(addressDao.getSearchField()).thenReturn(Address.getSearchField());
        System.out.println("auth: "+Address.SEARCH_FIELD);
        List<Address> addressList = new ArrayList<>();
        Address address1 = new Address("Suisse");
        addressList.add(address1);
        when(addressDao.getByCriteria(anyList())).thenReturn(searchList);
        assertNotNull(addressManager.searchByCriteria(searchList));
    }

    @Test
    @DisplayName("raise exception when invalid name")
    void searchByCriteria1() throws Exception {
        List<SearchDTO> searchList = new ArrayList<>();
        String fieldName = "named";
        SearchDTO dto1 = new SearchDTO(fieldName, "equals", "belgium");
        searchList.add(dto1);
        when(addressDao.getSearchField()).thenReturn(Address.getSearchField());
        Exception thrown =assertThrows(Exception.class,()->addressManager.searchByCriteria(searchList));

        assertEquals(fieldName.toUpperCase()+" is an invalid search criteria", thrown.getMessage());
    }

    @Test
    @DisplayName("raise exception when invalid compare")
    void searchByCriteria2() throws Exception {
        List<SearchDTO> searchList = new ArrayList<>();
        String fieldName = "name";
        SearchDTO dto = new SearchDTO(fieldName, "equales", "belgium");
        searchList.add(dto);
        when(addressDao.getSearchField()).thenReturn(Address.getSearchField());
        Exception thrown =assertThrows(Exception.class,()->addressManager.searchByCriteria(searchList));

        assertEquals(dto.getCompare().toUpperCase()+" is invalid or can't be used with "+ dto.getFieldName(), thrown.getMessage());
    }

    @Test
    @DisplayName("raise exception when invalid compare")
    void searchByCriteria3() throws Exception {
        List<SearchDTO> searchList = new ArrayList<>();
        String fieldName = "id";
        SearchDTO dto = new SearchDTO(fieldName, "equals", "belgium");
        searchList.add(dto);
        when(addressDao.getSearchField()).thenReturn(Address.getSearchField());
        Exception thrown =assertThrows(Exception.class,()->addressManager.searchByCriteria(searchList));

        assertEquals(dto.getValue().toUpperCase()+" is not a valid number", thrown.getMessage());
    }*/


    @Test
    void resetException() {
    }

    @Test
    void save() throws Exception {
        Country country = new Country();
        int id = 9;
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCity("Dundalk");
        addressDTO.setStreetName("test streetname");
        // addressDTO.setPostcode("dede");
        addressDTO.setCountryId(2);
        when(addressDao.create(any(Address.class))).thenReturn(id);
        when(countryDao.getById(anyInt())).thenReturn(country);
        assertEquals(Integer.toString(id), addressManager.save(addressDTO));
    }

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
