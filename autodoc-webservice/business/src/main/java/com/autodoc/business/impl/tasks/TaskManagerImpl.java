package com.autodoc.business.impl.tasks;

import com.autodoc.business.contract.tasks.TaskManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.tasks.TaskDaoImpl;
import com.autodoc.model.dtos.tasks.TaskDTO;
import com.autodoc.model.models.tasks.Task;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class TaskManagerImpl<T, D> extends AbstractGenericManager implements TaskManager {
    private TaskDaoImpl<Task> taskDao;
    private static final Logger LOGGER = Logger.getLogger(TaskManagerImpl.class);
    private ModelMapper mapper;

    public TaskManagerImpl(TaskDaoImpl<Task> taskDao) {
        super(taskDao);
        this.mapper = new ModelMapper();
        this.taskDao = taskDao;
    }


    @Override
    public TaskDTO entityToDto(Object entity) {
        TaskDTO dto = mapper.map(entity, TaskDTO.class);
        LOGGER.info("converted into dto");
        return dto;
    }

    @Override
    public Task dtoToEntity(Object entity) throws Exception {
        TaskDTO dto = (TaskDTO) entity;
        Task task = mapper.map(entity, Task.class);
        checkDataInsert(dto);
        return task;
    }


}
