package com.autodoc.controllers.impl.tasks;


import com.autodoc.business.contract.tasks.SubTaskManager;
import com.autodoc.business.contract.tasks.TemplateSubTaskManager;
import com.autodoc.controllers.contract.tasks.SubTaskController;
import com.autodoc.controllers.contract.tasks.TemplateSubTaskController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.controllers.impl.GlobalControllerImpl;
import com.autodoc.model.dtos.tasks.SubTaskDTO;
import com.autodoc.model.dtos.tasks.TemplateSubTaskDTO;
import com.autodoc.model.models.tasks.SubTask;
import com.autodoc.model.models.tasks.TemplateSubTask;
import org.apache.log4j.Logger;
import org.hibernate.sql.Template;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/templateSubTasks")
public class TemplateSubTaskControllerImpl extends GlobalControllerImpl<TemplateSubTask, TemplateSubTaskDTO> implements TemplateSubTaskController {
    private final static Logger LOGGER = Logger.getLogger(TemplateSubTaskControllerImpl.class);
    private TemplateSubTaskManager manager;
    private GsonConverter converter;

    public TemplateSubTaskControllerImpl(TemplateSubTaskManager manager) {
        super(manager);
        converter = new GsonConverter();
        this.manager = manager;
    }


    @Override
    public ResponseEntity getByName(String name) {
        return null;
    }
}
