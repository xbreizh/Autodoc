package com.autodoc.dao.impl.bill;

import com.autodoc.dao.contract.bill.BillDao;
import com.autodoc.dao.filler.Filler;
import com.autodoc.dao.filler.Remover;
import com.autodoc.model.models.bill.Bill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
class BillDaoImplTest {


    Bill obj;
    @Inject
    private BillDao dao;
    @Inject
    private Filler filler;
    @Inject
    private Remover remover;

    @BeforeEach
    void init() throws Exception {
        //  remover.remove();
        filler.fill();

    }


    @Test
    @DisplayName("should return search fields")
    void getSearchField() {

        assertEquals(Bill.getSearchField(), dao.getSearchField());
    }
}
