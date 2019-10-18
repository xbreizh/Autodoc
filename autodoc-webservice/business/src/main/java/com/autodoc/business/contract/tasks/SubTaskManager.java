package com.autodoc.business.contract.tasks;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.models.tasks.SubTask;

public interface SubTaskManager extends IGenericManager {


    String save(SubTask subTask);

    //  List<SubTask> getAll();
}
