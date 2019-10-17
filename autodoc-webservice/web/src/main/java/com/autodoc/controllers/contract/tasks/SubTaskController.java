package com.autodoc.controllers.contract.tasks;

import com.autodoc.model.models.person.employee.Employee;
import org.springframework.http.ResponseEntity;

public interface SubTaskController {


    ResponseEntity getAll();

    Employee getEmployeeByName(String name);

    String getEmployeeById(int id);

    String addEmployee(Employee employee);

    String updateEmployee();


    String deleteEmployee(int employeeId);
}
