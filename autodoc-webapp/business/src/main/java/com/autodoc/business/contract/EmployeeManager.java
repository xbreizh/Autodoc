package com.autodoc.business.contract;


import com.autodoc.model.models.person.employee.Employee;

public interface EmployeeManager extends GlobalManager {


    Employee getByLogin(String token, String login);

}
