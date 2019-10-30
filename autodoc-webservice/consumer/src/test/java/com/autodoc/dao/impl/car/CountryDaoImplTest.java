package com.autodoc.dao.impl.car;

import com.autodoc.dao.contract.person.provider.CountryDao;
import com.autodoc.model.enums.Compare;
import com.autodoc.model.models.search.Search;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
//@Sql(scripts = "classpath:resetDb_scripts/resetDbCar.sql")
@Transactional
class CountryDaoImplTest {

    @Inject
    private CountryDao dao;


    @BeforeEach
    void init() {

        String name = "PLOP";

    }

    @Test
    void getAll() {
        assertEquals(6, dao.getAll().size());
    }

    @Test
    void getByCriteria() throws Exception {
        Search search = new Search("id", Compare.NUMBERLESSOREQUALS, "33");
        List<Search> searchList = new ArrayList<>();
        searchList.add(search);
        assertTrue(!dao.getByCriteria(searchList).isEmpty());
    }
}