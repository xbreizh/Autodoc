package com.autodoc.business.contract.person.employee;

import com.autodoc.model.models.person.employee.Employee;

import java.util.List;

public interface EmployeeManager {

    boolean exist(String login);

    String save(Employee employee);

    List<Employee> getAll();

    Employee getEmployeeByLogin(String login);
}
