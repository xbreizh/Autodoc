package com.autodoc.controllers.contract.tasks;

import org.springframework.http.ResponseEntity;

public interface TaskController {


    ResponseEntity getByName(String name);


}
