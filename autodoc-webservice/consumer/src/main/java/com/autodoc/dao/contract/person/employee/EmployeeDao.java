package com.autodoc.dao.contract.person.employee;

import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.model.enums.Role;
import com.autodoc.model.models.person.employee.Employee;

import java.util.List;

public interface EmployeeDao extends IGenericDao {

    Employee getByLogin(String login);

    Employee getByToken(String token);

    List<Employee> getByRole(List<Role> roles);
}
