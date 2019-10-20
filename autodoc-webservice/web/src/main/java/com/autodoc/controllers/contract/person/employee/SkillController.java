package com.autodoc.controllers.contract.person.employee;

import org.springframework.http.ResponseEntity;

public interface SkillController {


    ResponseEntity getByName(String name);


}
