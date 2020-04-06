package com.autodoc.dao.impl.person.employee;

import com.autodoc.dao.contract.person.employee.EmployeeDao;
import com.autodoc.dao.filler.Filler;
import com.autodoc.dao.filler.Remover;
import com.autodoc.model.enums.Role;
import com.autodoc.model.models.employee.Employee;
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
@Transactional
class EmployeeDaoImplTest {

    Employee obj;
    @Inject
    private EmployeeDao dao;
    @Inject
    private Filler filler;
    @Inject
    private Remover remover;

    @BeforeEach
    void init() throws Exception {
        remover.remover();
        filler.fill();
        obj = (Employee) dao.getAll().get(0);
    }

    @Test
    @DisplayName("should return search fields")
    void getSearchField() {

        assertEquals(dao.getSearchField(), Employee.getSearchField());
    }

    @Test
    @DisplayName("should return obj if valid login")
    void getByLogin() {
        assertNotNull(dao.getByLogin(obj.getLogin()));

    }

    @Test
    @DisplayName("should return null if invalid login")
    void getByLogin1() {
        assertNull(dao.getByLogin("invalidLogin"));

    }

    @Test
    @DisplayName("should return obj if valid token")
    void getByToken() {
        obj.setToken("abc123");
        dao.update(obj);
        assertNotNull(dao.getByToken(obj.getToken()));

    }

    @Test
    @DisplayName("should return null if invalid token")
    void getByToken1() {
        assertNull(dao.getByToken("invalidToken"));

    }


    @Test
    @DisplayName("should return list if valid role and existing result")
    void getByRole() {
        List<Role> roles = new ArrayList<>();
        roles.add(Role.MECANIC);
        assertFalse(dao.getByRole(roles).isEmpty());
    }

    @Test
    @DisplayName("should return empty list if invalid role or no result")
    void getByRole1() {
        List<Role> roles = new ArrayList<>();
        roles.add(Role.MANAGER);
        roles.add(Role.MECANIC);
        roles.add(Role.SECRETARY);
        assertTrue(dao.getByRole(roles).isEmpty());
    }

    @Test
    @DisplayName("should return empty list if roles =null")
    void getByRole2() {
        assertTrue(dao.getByRole(null).isEmpty());
    }


    @Test
    @DisplayName("should return empty list if roles is empty")
    void getByRole3() {
        List<Role> roles = new ArrayList<>();
        assertTrue(dao.getByRole(roles).isEmpty());
    }


    @Test
    void deleteById() {
        int id = obj.getId() + 1;
        assertAll(
                () -> assertNotNull(dao.getById(id)),
                () -> assertTrue(dao.deleteById(id)),
                () -> assertNull(dao.getById(id))
        );
    }


}
