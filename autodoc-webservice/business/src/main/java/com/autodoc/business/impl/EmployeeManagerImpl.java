package com.autodoc.business.impl;

import com.autodoc.business.contract.EmployeeManager;
import com.autodoc.dao.impl.EmployeeDaoImpl;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.model.models.person.employee.Employee;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
public class EmployeeManagerImpl implements EmployeeManager {

    private EmployeeDaoImpl<Employee> employeeDao;

    public EmployeeManagerImpl(EmployeeDaoImpl<Employee> employeeDao) {
        this.employeeDao = employeeDao;
    }


    @Override
    public boolean exist(String login) {
        if (employeeDao.getByLogin(login) != null) return true;
        return false;
    }

    @Override
    public String save(Employee employee) {
         employeeDao.create(employee);
        return "employee saved";
    }

    @Override
    public List<Client> getAll() {
        return null;
    }
}
