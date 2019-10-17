package com.autodoc.controllers.contract.pieces;

import com.autodoc.model.models.person.employee.Employee;
import org.springframework.http.ResponseEntity;

public interface PieceTypeController {


    ResponseEntity getAll();

    Employee getEmployeeByName(String name);

    String getEmployeeById(int id);

    String addEmployee(Employee employee);

    String updateEmployee();


    String deleteEmployee(int employeeId);
}
