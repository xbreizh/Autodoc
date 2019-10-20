package com.autodoc.controllers.contract.person.employee;

import org.springframework.http.ResponseEntity;

public interface EmployeeController {


    ResponseEntity getByName(String name);

}
