package com.autodoc.business.impl.person.employee;

import com.autodoc.business.contract.person.employee.EmployeeManager;
import com.autodoc.dao.impl.person.employee.EmployeeDaoImpl;
import com.autodoc.model.models.person.employee.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EmployeeManagerImplTest {


    private EmployeeManager employeeManager;
    private EmployeeDaoImpl employeeDao;


    @BeforeEach
    void init() {
        employeeDao = mock(EmployeeDaoImpl.class);
        employeeManager = new EmployeeManagerImpl<>(employeeDao);
    }

    @Test
    void getAll() {
        List<Employee> list = new ArrayList<>();
        when(employeeDao.getAll()).thenReturn(list);
        assertNotNull(employeeManager.getAll());
    }


    @Test
    @DisplayName("should not return exception if valid roles")
    void checkRoleValuesValid() {
        String[] rolesArray = {"MANAGER", "Mecanic"};
        List<String> rolesToCheck = Arrays.asList(rolesArray);
        assertDoesNotThrow(
                ()->employeeManager.checkRoleValuesValid(rolesToCheck));

    }

    @Test
    @DisplayName("should return exception if invalid roles")
    void checkRoleValuesValid1() {
        String[] rolesArray = {"MANAGER", "Pharmacist"};
        List<String> rolesToCheck = Arrays.asList(rolesArray);
        assertThrows(Exception.class,
                ()->employeeManager.checkRoleValuesValid(rolesToCheck));

    }

    @Test
    void getByRoles() {
    }
}