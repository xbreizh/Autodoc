package com.autodoc.dao.impl.car;

import com.autodoc.dao.contract.person.provider.CountryDao;
import com.autodoc.dao.filler.Filler;
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
class CountryDaoImplTest {

    @Inject
    private CountryDao dao;

    @Inject
    private Filler filler;


    @BeforeEach
    void init() throws Exception {
        filler.fill();

    }

    @Test
    void getAll() {
        assertEquals(6, dao.getAll().size());
    }

    /*@Test
    void getByCriteria() throws Exception {
        Search search = new Search("name", SearchType., "e");
        Search search1 = new Search("id", Compare.NUMBERBIGGERTHAN, "2");
        List<Search> searchList = new ArrayList<>();
        searchList.add(search);
        searchList.add(search1);
        List<Country> list = dao.getByCriteria(searchList);
        System.out.println("list: "+list);
        assertTrue(!list.isEmpty());
    }*/

    @Test
    void getSearchFields() throws Exception {
        System.out.println(dao.getSearchField());
        assertNotNull(dao.getSearchField());
    }
}