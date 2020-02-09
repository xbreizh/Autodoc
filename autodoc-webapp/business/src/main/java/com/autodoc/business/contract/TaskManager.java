package com.autodoc.business.contract;


import com.autodoc.model.models.tasks.Task;

import java.util.List;

public interface TaskManager extends GlobalManager {

    List<Task> getTemplates(String token) throws Exception;

}
