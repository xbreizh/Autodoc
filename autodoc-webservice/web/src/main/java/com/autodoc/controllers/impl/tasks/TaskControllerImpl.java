package com.autodoc.controllers.impl.tasks;


import com.autodoc.business.contract.tasks.TaskManager;
import com.autodoc.controllers.contract.tasks.TaskController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.controllers.impl.GlobalControllerImpl;
import com.autodoc.model.dtos.tasks.TaskDTO;
import com.autodoc.model.models.tasks.Task;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskControllerImpl extends GlobalControllerImpl<Task, TaskDTO> implements TaskController {
    private final static Logger LOGGER = Logger.getLogger(TaskControllerImpl.class);
    private TaskManager manager;
    private GsonConverter converter;

    public TaskControllerImpl(TaskManager manager) {
        super(manager);
        converter = new GsonConverter();
        this.manager = manager;
    }

    @Override
    @GetMapping(value = "/templates",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getTemplates() {
        LOGGER.info("trying to get list of template");
        String response = converter.convertObjectIntoGsonObject(manager.getTemplates());
        LOGGER.info("response " + response);

        ResponseEntity entity = ResponseEntity.ok()
                .headers(responseHeaders)
                .body(response);
        return entity;
    }

    @Override
    @PutMapping(value = "/templates",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateTemplate(TaskDTO obj) throws Exception {
        LOGGER.debug("trying to update a " + obj);
        LOGGER.info("trying to update: " + obj);
        boolean response = manager.updateTemplate(obj);
        if (response) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @Override
    @DeleteMapping(value = "templates/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteTemplateById(@PathVariable Integer id) throws Exception {
        LOGGER.info("trying to delete");
        LOGGER.info("trying to delete: " + id);
        boolean response = manager.deleteTemplateById(id);
        if (!response) return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("invalid id: " + id);
        if (response) {
            return ResponseEntity.status(204).body(response);
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @PostMapping(value = "templates/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createFromTemplate(@PathVariable Integer id) throws Exception {
        LOGGER.info("trying to create from template");
        String response = manager.createFromTemplate(id).toString();
        try {
            int responseId = Integer.parseInt(response);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }
    }


}
