package com.autodoc.dao.impl.pieces;

import com.autodoc.dao.contract.car.CarModelDao;
import com.autodoc.dao.contract.person.provider.ProviderDao;
import com.autodoc.dao.contract.pieces.PieceDao;
import com.autodoc.dao.contract.pieces.PieceTypeDao;
import com.autodoc.dao.filler.Filler;
import com.autodoc.model.models.pieces.Piece;
import org.apache.log4j.Logger;
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
class PieceDaoImplTest {

    private static final Logger LOGGER = Logger.getLogger(PieceDaoImplTest.class);
    String name = "bozo";
    @Inject
    private PieceDao dao;
    @Inject
    private Filler filler;
    @Inject
    private PieceTypeDao pieceTypeDao;
    @Inject
    private ProviderDao providerDao;
    @Inject
    private CarModelDao carModelDao;
    private Piece obj;

    @BeforeEach
    void init() throws Exception {
        filler.fill();
        obj = (Piece) dao.getAll().get(0);
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
    @DisplayName("should return searchFields from Model")
    void getSearchField() {
        assertEquals(Piece.getSearchField(), dao.getSearchField());
    }
}
