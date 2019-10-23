package com.autodoc.controllers.impl.tasks;


import com.autodoc.business.contract.tasks.SubTaskManager;
import com.autodoc.controllers.contract.tasks.SubTaskController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.controllers.impl.GlobalControllerImpl;
import com.autodoc.model.dtos.tasks.SubTaskDTO;
import com.autodoc.model.models.tasks.SubTask;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/subTasks")
public class SubTaskControllerImpl extends GlobalControllerImpl<SubTask, SubTaskDTO> implements SubTaskController {
    private final static Logger LOGGER = Logger.getLogger(SubTaskControllerImpl.class);
    private SubTaskManager manager;
    private GsonConverter converter;

    public SubTaskControllerImpl(SubTaskManager manager) {
        super(manager);
        converter = new GsonConverter();
        this.manager = manager;
    }


    @Override
    public ResponseEntity getByName(String name) {
        return null;
    }
}
