/*
package com.autodoc.dao.impl.person.provider;

import com.autodoc.dao.contract.person.provider.CountryDao;
import com.autodoc.dao.filler.Filler;
import com.autodoc.model.models.person.provider.Country;
import com.autodoc.model.models.search.Search;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)*/
//@Sql(scripts = "classpath:resetDb_scripts/resetDbCar.sql")
/*
@Transactional
class CountryDaoImplTest {
    Country obj;
    @Inject
    private Filler filler;

    @Inject
    private CountryDao dao;

    @BeforeEach
    void init() throws ParseException {
        filler.fill();
        obj = (Country) dao.getAll().get(0);
    }

    @Test
    void getByCriteria() throws Exception {
        List<Search> searchList = new ArrayList<>();
        Search search = new Search("name", "like", "d");
        searchList.add(search);
        assertNotNull(dao.getByCriteria(searchList));
    }


    @Test
    @DisplayName("should return null if not existing")
    void getByName() {
        assertNull(dao.getByName("invalidName"));
    }


    @Test
    @DisplayName("should return obj if existing")
    void getByName1() {
        assertNotNull(dao.getByName(obj.getName()));
    }


    @Test
    @DisplayName("should return search fields")
    void getSearchField() {

        assertEquals(dao.getSearchField(), Country.getSearchField());
    }

}
*/
