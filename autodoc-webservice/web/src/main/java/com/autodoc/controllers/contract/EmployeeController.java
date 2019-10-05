package com.autodoc.controllers.contract;

import com.autodoc.model.models.person.employee.Employee;

public interface EmployeeController {


    String getAll();

    Employee getEmployeeByName(String name);

    String getEmployeeById(int id);

    String addEmployee(Employee employee);

    String updateEmployee();


    String deleteEmployee(int employeeId);
}
