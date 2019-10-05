package com.autodoc.business.contract.tasks;

import com.autodoc.model.models.tasks.TemplateSubTask;

import java.util.List;

public interface TemplateSubTaskManager {


    String save(TemplateSubTask templateSubTask);

    List<TemplateSubTask> getAll();
}
