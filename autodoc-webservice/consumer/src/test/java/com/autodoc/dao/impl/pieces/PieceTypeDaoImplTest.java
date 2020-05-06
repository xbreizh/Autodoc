package com.autodoc.dao.impl.pieces;

import com.autodoc.dao.contract.pieces.PieceTypeDao;
import com.autodoc.dao.filler.Filler;
import com.autodoc.model.models.pieces.PieceType;
import org.apache.log4j.BasicConfigurator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
@Transactional
class PieceTypeDaoImplTest {


    PieceType obj;
    @Inject
    private PieceTypeDao dao;
    @Inject
    private Filler filler;

    @BeforeEach
    void init()  throws Exception {
        BasicConfigurator.configure();
        filler.fill();
        obj = (PieceType) dao.getAll().get(0);

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

        assertEquals(dao.getSearchField(), PieceType.getSearchField());
    }
}
