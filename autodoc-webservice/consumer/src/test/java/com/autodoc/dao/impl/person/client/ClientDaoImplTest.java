package com.autodoc.dao.impl.person.client;

import com.autodoc.dao.filler.Filler;
import org.junit.jupiter.api.BeforeEach;

import javax.inject.Inject;

class ClientDaoImplTest {

    @Inject
    private Filler filler;


    @BeforeEach
    void init() throws Exception {
        filler.fill();

    }

   /* @Test
    void getClazz() {
    }

    @Test
    void setSessionFactory() {
    }

    @Test
    void setClazz() {
    }

    @Test
    void findOne() {
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
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
    void getCurrentSession() {
    }*/
}