package com.autodoc.dao.impl.person.employee;

import com.autodoc.dao.contract.person.employee.EmployeeDao;
import com.autodoc.dao.filler.Filler;
import com.autodoc.model.enums.Role;
import com.autodoc.model.models.employee.Employee;
import org.apache.log4j.BasicConfigurator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
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

    @BeforeEach
    void init()  throws Exception {
       // BasicConfigurator.configure();
        filler.fill();
        obj = (Employee) dao.getAll().get(0);
    }

    @Test
    @DisplayName("should return search fields")
    void getSearchField() {

        assertEquals(Employee.getSearchField(), dao.getSearchField());
    }

    @Test
    @DisplayName("should return obj if valid login")
    void getByLogin() {
        assertNotNull(dao.getByLogin(obj.getLogin()));

    }

    @Test
    @DisplayName("should return obj if valid id")
    void getById() {
        assertNotNull(dao.getById(obj.getId()));

    }

    @Test
    @DisplayName("should return obj if valid id")
    void getById1() {
        assertNull(dao.getById(9999));

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
    @Disabled
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

/*
    @Test
    void update() {
       Employee employee = (Employee) dao.getById(2);
       Date date = new Date();
       employee.setStartDate(date);
    //   dao.update(employee);
       */
/*assertEquals(date, ((Employee) dao.getById(1)).getStartDate());*//*

        Employee employee1 = (Employee) dao.getById(2);
        System.out.println("date: "+employee1.getStartDate());

    }
*/


}
