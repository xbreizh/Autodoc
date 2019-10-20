package com.autodoc.controllers.contract.tasks;

import org.springframework.http.ResponseEntity;

public interface SubTaskController {


    ResponseEntity getByName(String name);


}
