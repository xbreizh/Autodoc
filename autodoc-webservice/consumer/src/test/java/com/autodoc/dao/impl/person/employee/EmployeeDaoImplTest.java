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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
//@Sql(scripts = "classpath:resetDb_scripts/resetDbCar.sql")
@Transactional
class EmployeeDaoImplTest {


    @Inject
    private EmployeeDao employeeDao;
    @Inject
    private Filler filler;

    @BeforeEach
    void init() throws Exception {
        filler.fill();

    }

    @Test
    void getAll() {
        assertEquals(2, employeeDao.getAll().size());
    }


    @Test
    void getByLogin() {
        assertNotNull( employeeDao.getByLogin("LMOLO"));

    }

    /*@Test
    void getByToken() {
        Employee employee = (Employee) employeeDao.getAll().get(0);
        String token = employee.getToken();
        assertEquals(employee, employeeDao.getByToken(token));
    }*/

    @Test
    void getByRole() {
        List<Role> roles = new ArrayList<>();
        roles.add(Role.MANAGER);
        roles.add(Role.MECANIC);
        Employee employee = (Employee) employeeDao.getAll().get(0);
        System.out.println("roles: " + employee.getRoles());
        System.out.println("size avl" + employeeDao.getAll().get(0));
        assertEquals(1, employeeDao.getByRole(roles).size());
    }



}