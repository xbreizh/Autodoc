package com.autodoc.business.impl;

import com.autodoc.business.contract.EmployeeManager;
import com.autodoc.dao.contract.EmployeeDao;
import com.autodoc.model.models.person.employee.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EmployeeManagerImplTest {


    private EmployeeManager employeeManager;
    private EmployeeDao employeeDao;

    @BeforeEach
    void init(){
        employeeDao = mock(EmployeeDao.class);
        employeeManager = new EmployeeManagerImpl(employeeDao);
    }


    @Test
    @DisplayName("should return false if employee does not exist")
    void exist() {
        when(employeeDao.getByLogin(anyString())).thenReturn(null);
        assertFalse(employeeManager.exist("ploc"));
    }

    @Test
    @DisplayName("should return true if employee does exist")
    void exist1() {
        when(employeeDao.getByLogin(anyString())).thenReturn(new Employee());
        assertTrue(employeeManager.exist("ploc"));
    }
}