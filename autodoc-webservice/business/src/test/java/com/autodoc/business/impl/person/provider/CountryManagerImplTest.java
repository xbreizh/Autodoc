package com.autodoc.business.impl.person.provider;

import com.autodoc.business.contract.person.provider.CountryManager;
import com.autodoc.dao.impl.person.provider.CountryDaoImpl;
import com.autodoc.model.models.car.Manufacturer;
import com.autodoc.model.models.person.provider.Country;
import com.autodoc.model.models.search.SearchDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CountryManagerImplTest {


    private CountryManager countryManager;
    private CountryDaoImpl countryDao;


    @BeforeEach
    void init() {
        countryDao = mock(CountryDaoImpl.class);
        countryManager = new CountryManagerImpl(countryDao);
    }

    @Test
    void getAll() {
        List<Manufacturer> list = new ArrayList<>();
        when(countryDao.getAll()).thenReturn(list);
        assertNotNull(countryManager.getAll());
    }


    @Test
    void searchByCriteria() throws Exception {
        List<SearchDTO> searchList = new ArrayList<>();
        SearchDTO dto1 = new SearchDTO("name", "equals", "belgium");
        searchList.add(dto1);
        when(countryDao.getSearchField()).thenReturn(Country.getSearchField());
        LOGGER.info("auth: " + Country.SEARCH_FIELD);
        List<Country> countryList = new ArrayList<>();
        Country country1 = new Country("Suisse");
        countryList.add(country1);
        when(countryDao.getByCriteria(anyList())).thenReturn(searchList);
        assertNotNull(countryManager.searchByCriteria(searchList));
    }

    @Test
    @DisplayName("raise exception when invalid name")
    void searchByCriteria1() throws Exception {
        List<SearchDTO> searchList = new ArrayList<>();
        String fieldName = "named";
        SearchDTO dto1 = new SearchDTO(fieldName, "equals", "belgium");
        searchList.add(dto1);
        when(countryDao.getSearchField()).thenReturn(Country.getSearchField());
        Exception thrown =assertThrows(Exception.class,()->countryManager.searchByCriteria(searchList));

        assertEquals(fieldName.toUpperCase()+" is an invalid search criteria", thrown.getMessage());
    }

    @Test
    @DisplayName("raise exception when invalid compare")
    void searchByCriteria2() throws Exception {
        List<SearchDTO> searchList = new ArrayList<>();
        String fieldName = "name";
        SearchDTO dto = new SearchDTO(fieldName, "equales", "belgium");
        searchList.add(dto);
        when(countryDao.getSearchField()).thenReturn(Country.getSearchField());
        Exception thrown =assertThrows(Exception.class,()->countryManager.searchByCriteria(searchList));

        assertEquals(dto.getCompare().toUpperCase()+" is invalid or can't be used with "+ dto.getFieldName(), thrown.getMessage());
    }

    @Test
    @DisplayName("raise exception when invalid compare")
    void searchByCriteria3() throws Exception {
        List<SearchDTO> searchList = new ArrayList<>();
        String fieldName = "id";
        SearchDTO dto = new SearchDTO(fieldName, "equals", "belgium");
        searchList.add(dto);
        when(countryDao.getSearchField()).thenReturn(Country.getSearchField());
        Exception thrown =assertThrows(Exception.class,()->countryManager.searchByCriteria(searchList));

        assertEquals(dto.getValue().toUpperCase()+" is not a valid number", thrown.getMessage());
    }


    @Test
    void resetException() {
    }

    @Test
    void save() {
    }

    @Test
    void checkDataInsert() {
    }

    @Test
    void checkDataUpdate() {
    }

    @Test
    void getById() {
    }

    @Test
    void getByName() {
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
