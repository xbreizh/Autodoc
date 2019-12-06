package com.autodoc.controllers.contract.tasks;

import com.autodoc.controllers.contract.GlobalController;
import com.autodoc.model.dtos.tasks.TaskDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface TaskController<D> extends GlobalController {


    ResponseEntity getTemplates();

    ResponseEntity updateTemplate(@RequestBody TaskDTO obj) throws Exception;

    ResponseEntity deleteTemplateById(@PathVariable Integer id) throws Exception;


    ResponseEntity createFromTemplate(@PathVariable Integer id) throws Exception;


}
