package com.autodoc.dao.impl.pieces;

import com.autodoc.dao.contract.car.CarModelDao;
import com.autodoc.dao.contract.person.provider.ProviderDao;
import com.autodoc.dao.contract.pieces.PieceDao;
import com.autodoc.dao.contract.pieces.PieceTypeDao;
import com.autodoc.dao.filler.Filler;
import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.person.provider.Provider;
import com.autodoc.model.models.pieces.Piece;
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
    private Piece piece;

    @BeforeEach
    void init() throws Exception {
        filler.fill();
        LOGGER.info("here");
        CarModel carModel = (CarModel) carModelDao.getAll().get(0);
        Provider provider = (Provider) providerDao.getAll().get(0);
        PieceType pieceType = (PieceType) pieceTypeDao.getAll().get(0);
        piece = new Piece();
        piece.setName("paltoquet");
        piece.setBrand(name);
        piece.setBuyingPrice(12);
        piece.setSellPrice(24);
        //piece.setCarModel(carModel);
        piece.setProvider(provider);
        piece.setPieceType(pieceType);
        filler.fill();
    }

    @Test
    void getAll() {
        LOGGER.info("emp[l" + dao);
        assertEquals(4, dao.getAll().size());
    }


    @Test
    void getById() {
        assertNotNull(dao.getById(94));
    }

    @Test
    void getByName() {
    }


    @Test
    void create() {
        assertNotEquals(0, dao.create(piece));
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void getByCriteria() {
    }

    @Test
    void buildCriteriaRequest() {
    }

    @Test
    void isValidDate() {
    }

    @Test
    void getSearchField() {
    }
}