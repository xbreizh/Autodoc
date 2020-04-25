package com.autodoc.dao.filler;

import com.autodoc.dao.contract.bill.BillDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
@Transactional
class RemoverTest {

    @Inject
    Filler filler;
    @Inject
    Remover remover;
    @Inject
    BillDao billDao;


    @Test
    void checkFill() throws Exception {
        filler.fill();
        assertFalse(billDao.getAll().isEmpty());
    }

    @Test
    void checkRemover() {
        remover.cleanup();
        assertTrue(billDao.getAll().isEmpty());
    }


}
