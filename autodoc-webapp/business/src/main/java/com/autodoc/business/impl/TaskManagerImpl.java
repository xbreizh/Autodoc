package com.autodoc.business.impl;


import com.autodoc.business.contract.TaskManager;
import com.autodoc.contract.GlobalService;
import com.autodoc.contract.TaskService;
import com.autodoc.model.dtos.tasks.TaskDTO;
import com.autodoc.model.dtos.tasks.TaskForm;
import com.autodoc.model.models.tasks.Task;
import lombok.Builder;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
@Builder
public class TaskManagerImpl extends GlobalManagerImpl<Task, TaskDTO> implements TaskManager {

    private static final Logger LOGGER = Logger.getLogger(TaskManagerImpl.class);
    //private Task task;
    private TaskService service;

    GlobalService getService() {
        return service;
    }

    public Task dtoToEntity(String token, Object obj) {

        TaskDTO dto = (TaskDTO) obj;
        LOGGER.info("dto: " + dto);
        Task task = new Task();
        int id = dto.getId();
        LOGGER.info("id: " + id);
        task.setId(id);
        task.setName(dto.getName());
        task.setDescription(dto.getDescription());
        task.setEstimatedTime(dto.getEstimatedTime());
        LOGGER.info("task transferred: " + task);

        return task;
    }

    public void update(String token, Object obj) {
        LOGGER.info("updating task: " + obj);
        TaskDTO taskDto = formToDto(obj, token);
        LOGGER.info("regular update: " + taskDto);
        service.update(token, taskDto);
    }


    public TaskDTO formToDto(Object obj, String token) {
        LOGGER.info("stuff to update: " + obj);
        TaskForm form = (TaskForm) obj;
        LOGGER.info("dto: " + form);
        TaskDTO dto = new TaskDTO();
        dto.setId(form.getId());
        dto.setName(form.getName());
        dto.setDescription(form.getDescription());
        dto.setEstimatedTime(form.getEstimatedTime());
        LOGGER.info("task transferred: " + dto);
        return dto;
    }


}
