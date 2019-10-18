package com.autodoc.business.contract.tasks;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.models.tasks.TemplateSubTask;

public interface TemplateSubTaskManager extends IGenericManager {


    String save(TemplateSubTask templateSubTask);

    //  List<TemplateSubTask> getAll();
}
