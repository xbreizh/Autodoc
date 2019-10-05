package com.autodoc.business.contract.tasks;

import com.autodoc.model.models.tasks.SubTask;

import java.util.List;

public interface SubTaskManager {


    String save(SubTask subTask);

    List<SubTask> getAll();
}
