/*
package com.autodoc.spring.controller.impl;

import com.autodoc.business.contract.EmployeeManager;
import com.autodoc.business.impl.EmployeeManagerImpl;
import com.autodoc.helper.LibraryHelper;
import com.autodoc.helper.LibraryHelperImpl;
import com.autodoc.helper.PasswordCheckerImpl;
import com.autodoc.model.models.person.employee.Employee;
import com.autodoc.security.config.WebConfig;
import com.autodoc.spring.controller.contract.EmployeeController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;


@ExtendWith({SpringExtension.class})
//@ContextConfiguration(locations = "classpath:mvc-dispatcher-servlet.xml")
@WebAppConfiguration
@ContextConfiguration(classes = {WebSecurity.class, WebConfig.class})
//@Sql(scripts = "classpath:resetDb.sql")
//@Transactional
class EmployeeControllerImplTest {


    @Inject
    EmployeeController controller;
    EmployeeManager employeeManager;
    List<Employee> employees = new ArrayList<>();
    Employee employee;
    @Inject
    LibraryHelper helper;
    @Inject
    GlobalController globalController;
    PasswordCheckerImpl checker;

    @BeforeEach
    void init() {
        employeeManager = mock(EmployeeManagerImpl.class);
        helper = mock(LibraryHelperImpl.class);
        controller = new EmployeeControllerImpl(helper, employeeManager);
        checker = mock(PasswordCheckerImpl.class);
        controller.setEmployeeManager(employeeManager);
        globalController.setHelper(helper);
        controller.setEmployeeManager(employeeManager);
        String role = "ADMIN";
        List<String> roles = new ArrayList<>();
        roles.add(role);
        employee = new Employee(2, "dalton", "3232323", "0862767399", roles, new Date(), "marko", new Date());
        employees.add(employee);
    }

 */
/*   @Test
    void employees() {
        LOGGER.info(employeeManager);
        when(helper.getConnectedToken()).thenReturn("token123");
        when(helper.getConnectedLogin()).thenReturn("popeye");
        when(employeeManager.getAll(anyString())).thenReturn(employees);
        when(employeeManager.getByLogin("token123", "popeye")).thenReturn(employee);
        LOGGER.info(employeeManager.getAll("dede").get(0));
       // LOGGER.info("empl: " + controller.employees());
        assertAll(
                () -> assertNotNull(controller.employees())
        );
    }*//*

}*/
