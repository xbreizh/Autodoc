package com.autodoc.dao.contract;

import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.model.models.person.employee.Employee;

public interface EmployeeDao extends IGenericDao {

    Employee getByLogin(String login);
}
