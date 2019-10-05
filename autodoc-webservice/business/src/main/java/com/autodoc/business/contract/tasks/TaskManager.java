package com.autodoc.business.contract.tasks;

import com.autodoc.model.models.tasks.Task;

import java.util.List;

public interface TaskManager {


    String save(Task task);

    List<Task> getAll();
}
