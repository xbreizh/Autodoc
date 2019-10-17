package com.autodoc.controllers.contract.person.employee;

import com.autodoc.model.models.person.employee.Employee;

public interface SkillCategoryController {


    String getAll();

    Employee getEmployeeByName(String name);

    String getEmployeeById(int id);

    String addEmployee(Employee employee);

    String updateEmployee();


    String deleteEmployee(int employeeId);
}
