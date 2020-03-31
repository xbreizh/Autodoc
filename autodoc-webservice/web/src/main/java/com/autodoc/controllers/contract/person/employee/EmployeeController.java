package com.autodoc.controllers.contract.person.employee;

import com.autodoc.model.dtos.RoleListDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeController {


    ResponseEntity getByName(String name);

    ResponseEntity getByRoles(List<RoleListDTO> roles) throws Exception;

}
