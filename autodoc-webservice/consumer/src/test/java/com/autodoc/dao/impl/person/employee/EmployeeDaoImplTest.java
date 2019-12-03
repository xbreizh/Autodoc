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
    private EmployeeDao employeeDao;
    @Inject
    private Filler filler;


    @BeforeEach
    void init() throws Exception {
        filler.fill();
        System.out.println("here");
    }

    @Test
    void getAll() {
        System.out.println("emp[l" + employeeDao);
        assertEquals(2, employeeDao.getAll().size());
    }


    @Test
    void getByLogin() {
        assertNotNull(employeeDao.getByLogin("LMOLO"));

    }


    @Test
    void getByRole() {

        List<Role> roles = new ArrayList<>();
        roles.add(Role.MANAGER);
        roles.add(Role.MECANIC);
        assertNotNull(employeeDao.getByRole(roles));
    }

    @Test
    void update() {
        int id = 2;
        String login = "MOLOK";
        Employee employee = (Employee) employeeDao.getById(id);
        employee.setLogin(login);
        assertEquals(login, ((Employee) employeeDao.getById(id)).getLogin());
    }

    @Test
    void deleteById() {
        int id = 2;
        assertNotNull(employeeDao.getById(id));
        employeeDao.deleteById(id);
        assertNull(employeeDao.getById(id));
    }

    @Test
    void deleteByObject() {
        int id = 2;
        Employee employee = (Employee) employeeDao.getById(id);
        assertNotNull(employee);
        employeeDao.delete(employee);
        assertNull(employeeDao.getById(id));
    }


}