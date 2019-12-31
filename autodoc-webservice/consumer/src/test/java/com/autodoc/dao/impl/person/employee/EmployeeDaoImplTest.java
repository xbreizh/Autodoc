package com.autodoc.dao.impl.person.employee;

import com.autodoc.dao.contract.person.employee.EmployeeDao;
import com.autodoc.dao.filler.Filler;
import com.autodoc.model.enums.Role;
import com.autodoc.model.models.person.employee.Employee;
import org.junit.jupiter.api.BeforeEach;
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
@Transactional
class EmployeeDaoImplTest {


    @Inject
    private EmployeeDao dao;
    @Inject
    private Filler filler;


    @BeforeEach
    void init() throws Exception {
        filler.fill();
        LOGGER.info("here");
    }

    @Test
    void getAll() {
        LOGGER.info("emp[l" + dao);
        assertEquals(2, dao.getAll().size());
    }


    @Test
    void getByLogin() {
        assertNotNull(dao.getByLogin("LMOLO"));

    }


    @Test
    void getByRole() {

        List<Role> roles = new ArrayList<>();
        roles.add(Role.MANAGER);
        roles.add(Role.MECANIC);
        assertNotNull(dao.getByRole(roles));
    }

    @Test
    void update() {
        int id = 2;
        String login = "MOLOK";
        Employee employee = (Employee) dao.getById(id);
        employee.setLogin(login);
        assertEquals(login, ((Employee) dao.getById(id)).getLogin());
    }

    @Test
    void deleteById() {
        int id = 2;
        assertAll(
                () -> assertNotNull(dao.getById(id)),
                () -> assertTrue(dao.deleteById(id)),
                () -> assertNull(dao.getById(id))
        );
    }


    @Test
    void deleteByObject() {
        int id = 2;
        Employee employee = (Employee) dao.getById(id);
        assertNotNull(employee);
        dao.delete(employee);
        assertNull(dao.getById(id));
    }


}