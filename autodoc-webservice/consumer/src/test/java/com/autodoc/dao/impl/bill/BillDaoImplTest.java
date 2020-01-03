package com.autodoc.dao.impl.bill;

import com.autodoc.dao.contract.bill.BillDao;
import com.autodoc.dao.filler.Filler;
import com.autodoc.model.models.bill.Bill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
//@Sql(scripts = "classpath:resetDb_scripts/resetDbCar.sql")
@Transactional
class
BillDaoImplTest {


    String clientName;
    String registration;
    int id;
    Bill bill;
    @Inject
    private BillDao dao;
    @Inject
    private Filler filler;

    @BeforeEach
    void init() throws Exception {
        filler.fill();
        bill = (Bill) dao.getAll().get(0);
        id = bill.getId();

    }

    @Test
    void getAll() {
        assertEquals(3, dao.getAll().size());
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {

        assertTrue(dao.deleteById(2));
    }
}