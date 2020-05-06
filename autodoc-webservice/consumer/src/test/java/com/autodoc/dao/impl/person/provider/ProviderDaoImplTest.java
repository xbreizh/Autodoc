package com.autodoc.dao.impl.person.provider;

import com.autodoc.dao.contract.person.provider.ProviderDao;
import com.autodoc.dao.filler.Filler;
import com.autodoc.model.models.person.provider.Provider;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
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
@Transactional
class ProviderDaoImplTest {
    private static final Logger LOGGER = Logger.getLogger(ProviderDaoImplTest.class);

    @Inject
    private ProviderDao dao;

    @Inject
    private Filler filler;


    @BeforeEach
    void init()  throws Exception {
        BasicConfigurator.configure();
        filler.fill();
        LOGGER.info("here");
    }


    @Test
    @DisplayName("should return search fields")
    void getSearchField() {

        assertEquals(dao.getSearchField(), Provider.getSearchField());
    }
}
