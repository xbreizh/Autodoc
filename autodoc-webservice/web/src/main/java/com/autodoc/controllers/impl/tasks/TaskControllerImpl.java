package com.autodoc.controllers.impl.tasks;


import com.autodoc.business.contract.IGenericManager;
import com.autodoc.business.contract.tasks.TaskManager;
import com.autodoc.controllers.contract.tasks.TaskController;
import com.autodoc.controllers.impl.GlobalControllerImpl;
import com.autodoc.model.dtos.tasks.TaskDTO;
import com.autodoc.model.models.tasks.Task;
import lombok.Builder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tasks")
@Builder
public class TaskControllerImpl extends GlobalControllerImpl<Task, TaskDTO> implements TaskController {
    private final static Logger LOGGER = Logger.getLogger(TaskControllerImpl.class);
    private TaskManager manager;

    public IGenericManager getManager() {
        return manager;
    }

}
