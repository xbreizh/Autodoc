package com.autodoc.business.contract;

import com.autodoc.model.models.person.client.Client;
import com.autodoc.model.models.person.employee.Employee;

import java.util.List;

public interface EmployeeManager {

    boolean exist(String login);

    String save(Employee employee);

    List<Client> getAll();
}
