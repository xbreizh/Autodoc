package com.autodoc.dao.impl.person.client;

import com.autodoc.dao.contract.person.client.ClientDao;
import com.autodoc.dao.filler.Filler;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.model.models.search.Search;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
//@Sql(scripts = "classpath:resetDb_scripts/resetDbCar.sql")
@Transactional
class ClientDaoImplTest {

    Client client = null;

    @Inject
    private Filler filler;
    @Inject
    private ClientDao clientDao;
    private int id;
    private String name;

    @BeforeEach
    void init() throws Exception {
        filler.fill();
        client = (Client) clientDao.getAll().get(0);
        id = client.getId();
        name = client.getLastName();

    }


    @Test
    @DisplayName("should return null if invalid id")
    void getById() {

        assertNull(clientDao.getById(999));
    }

    @Test
    @DisplayName("should return client if valid id")
    void getById1() {

        assertEquals(client, clientDao.getById(id));
    }

    @Test
    @DisplayName("should return a list")
    void searchByCriteria() throws Exception {
        Client client = (Client) clientDao.getById(2);
        List<Search> searchList = new ArrayList<>();
        Search search1 = new Search("firstName", "=", client.getFirstName().toUpperCase());
        Search search2 = new Search("lastName", "=", client.getLastName().toUpperCase());
        Search search3 = new Search("phoneNumber", "=", client.getPhoneNumber().toUpperCase());
        searchList.add(search1);
        searchList.add(search2);
        searchList.add(search3);
        assertFalse(clientDao.getByCriteria(searchList).isEmpty());
    }


    @Test
    @DisplayName("should return an empty list")
    void searchByCriteria1() throws Exception {
        Client client = (Client) clientDao.getById(2);
        List<Search> searchList = new ArrayList<>();
        Search search1 = new Search("firstName", "=", "rodolf");
        Search search2 = new Search("lastName", "=", client.getLastName().toUpperCase());
        Search search3 = new Search("phoneNumber", "=", client.getPhoneNumber().toUpperCase());
        searchList.add(search1);
        searchList.add(search2);
        searchList.add(search3);
        assertTrue(clientDao.getByCriteria(searchList).isEmpty());
    }

    @Test
    @DisplayName("should return client if valid name")
    void getByName() {

        assertNotNull(clientDao.getByName("MOLO"));
    }

    @Test
    void findAll() {
        assertNotNull(clientDao.getAll());
    }

    @Test
    void create() {
        Client client2 = new Client();
        client2.setFirstName("Bob");
        client2.setLastName("Moran");
        client2.setPhoneNumber("2133232");
        int id = clientDao.create(client2);
        System.out.println("id returned: "+id);
        assertNotEquals(0, id);
    }

    @Test
    void delete() {
        assertAll(
                () -> assertTrue(clientDao.delete(client)),
                () -> assertNull(clientDao.getById(id))
        );

    }

    @Test
    void update() {
        String firstName = "Bruno";
        client.setFirstName(firstName);
        boolean response = clientDao.update(client);
        Client client = (Client) clientDao.getById(id);
        assertAll(
                () -> assertTrue(response),
                () -> assertEquals(firstName, client.getFirstName())
        );
    }

    @Test
    void deleteById() {
        assertAll(
                () -> assertTrue(clientDao.deleteById(id)),
                () -> assertNull(clientDao.getById(id))
        );
    }


}