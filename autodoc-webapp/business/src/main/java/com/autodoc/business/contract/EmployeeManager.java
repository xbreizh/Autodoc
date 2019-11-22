package com.autodoc.business.contract;


import com.autodoc.model.models.Employee;

public interface EmployeeManager extends GlobalManager{


    Employee getByLogin(String token, String login);

}
