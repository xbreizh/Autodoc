package com.autodoc.business.contract.person.employee;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.models.person.employee.Employee;

public interface EmployeeManager extends IGenericManager {

    boolean exist(String login);

    String save(Employee employee);

    // List<Employee> getAll();

    Employee getEmployeeByLogin(String login);

    Employee getEmployeeByToken(String token);
}
