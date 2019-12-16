package com.autodoc.business.contract;


import com.autodoc.model.models.person.employee.Employee;

import java.util.List;

public interface EmployeeManager extends GlobalManager {


    Employee getByLogin(String token, String login);

    int getMax(String token);

    List<String> getRoles(String token);

}
