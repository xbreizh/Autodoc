package com.autodoc.business.contract.tasks;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.models.tasks.Task;

public interface TaskManager extends IGenericManager {


    String save(Task task);

    //  List<Task> getAll();
}
