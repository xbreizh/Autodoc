package com.autodoc.controllers.impl.tasks;


import com.autodoc.business.contract.tasks.TaskManager;
import com.autodoc.controllers.contract.tasks.TaskController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.controllers.impl.GlobalControllerImpl;
import com.autodoc.model.dtos.tasks.TaskDTO;
import com.autodoc.model.models.tasks.Task;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public ResponseEntity getByName(String name) {
        return null;
    }
}
