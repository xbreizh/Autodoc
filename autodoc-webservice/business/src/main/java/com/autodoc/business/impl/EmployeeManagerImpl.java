package com.autodoc.business.impl;

import com.autodoc.business.contract.EmployeeManager;
import com.autodoc.dao.contract.EmployeeDao;

public class EmployeeManagerImpl implements EmployeeManager {

    private EmployeeDao employeeDao;

    public EmployeeManagerImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }


    @Override
    public boolean exist(String login) {
        if (employeeDao.getByLogin(login) != null) return true;
        return false;
    }
}
