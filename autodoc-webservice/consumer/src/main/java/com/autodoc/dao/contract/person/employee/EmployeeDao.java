package com.autodoc.dao.contract.person.employee;

import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.model.models.person.employee.Employee;

public interface EmployeeDao extends IGenericDao {

    Employee getByLogin(String login);

    Employee getByToken(String token);
}
