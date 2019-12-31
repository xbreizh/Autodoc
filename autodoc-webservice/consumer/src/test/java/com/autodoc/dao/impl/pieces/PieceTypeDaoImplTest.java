package com.autodoc.dao.impl.pieces;

import com.autodoc.dao.contract.pieces.PieceTypeDao;
import com.autodoc.dao.filler.Filler;
import com.autodoc.model.models.pieces.PieceType;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
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

    private static final Logger LOGGER = Logger.getLogger(PieceTypeDaoImplTest.class);

    String clientName;
    String registration;
    int id;
    PieceType pieceType;
    @Inject
    private PieceTypeDao dao;
    @Inject
    private Filler filler;

    @BeforeEach
    void init() throws Exception {
        filler.fill();
        pieceType = (PieceType) dao.getAll().get(0);
        id = pieceType.getId();

    }


    @Test
    void getAll() {
        assertEquals(6, dao.getAll().size());
    }

    @Test
    void getById() {
        assertNotNull(dao.getById(2));
    }

    @Test
    void getByName() {
        assertNotNull(dao.getByName("SCREW"));
    }


    @Test
    void create() {
        PieceType pieceType = new PieceType();
        pieceType.setName("TYRE");
        LOGGER.info(dao.create(pieceType));
        assertNotEquals(0, dao.create(pieceType));
    }

    @Test
    void delete() {
        assertNotNull(dao.getById(2));
        dao.deleteById(2);
        assertNull(dao.getById(2));

    }
}