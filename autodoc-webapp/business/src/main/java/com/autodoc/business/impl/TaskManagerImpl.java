package com.autodoc.business.impl;


import com.autodoc.business.contract.TaskManager;
import com.autodoc.contract.TaskService;
import com.autodoc.model.dtos.tasks.TaskDTO;
import com.autodoc.model.dtos.tasks.TaskForm;
import com.autodoc.model.models.tasks.Task;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
public class TaskManagerImpl extends GlobalManagerImpl<Task, TaskDTO> implements TaskManager {

    private static final Logger LOGGER = Logger.getLogger(TaskManagerImpl.class);
    public Task task;
    private TaskService service;

    public TaskManagerImpl(TaskService service) {
        super(service);
        this.service = service;
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
        task.setPrice(dto.getPrice());
        String templateValue = dto.getTemplate();
        if (templateValue.equalsIgnoreCase("true") || templateValue.equalsIgnoreCase("false"))
            task.setTemplate(Boolean.valueOf(templateValue));
        LOGGER.info("task transferred: " + task);

        return task;
    }

    public TaskDTO formToDto(Object obj) {
        LOGGER.info("stuff to update: " + obj);
        TaskForm dto = (TaskForm) obj;
        LOGGER.info("dto: " + dto);
        TaskDTO task = new TaskDTO();
        task.setName(dto.getName());
        task.setDescription(dto.getDescription());
        task.setEstimatedTime(dto.getEstimatedTime());
        task.setPrice(dto.getPrice());
        String templateValue = dto.getTemplate();
        if (templateValue.equalsIgnoreCase("true") || templateValue.equalsIgnoreCase("false"))
            task.setTemplate(templateValue);
        LOGGER.info("task transferred: " + task);
        return task;
    }

/*    List<Task> convertList(String token, List<TaskDTO> list) {
        LOGGER.info("converting list: "+list);
        List<Task> newList = new ArrayList<>();
        for (TaskDTO obj : list) {
            newList.add(dtoToEntity(token, obj));
        }
        LOGGER.info("new list: "+newList);
        return newList;
    }*/

}
