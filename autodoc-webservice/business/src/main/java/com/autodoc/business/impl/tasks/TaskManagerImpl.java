package com.autodoc.business.impl.tasks;

import com.autodoc.business.contract.tasks.TaskManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.tasks.TaskDaoImpl;
import com.autodoc.model.models.tasks.Task;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class TaskManagerImpl<T, D> extends AbstractGenericManager implements TaskManager {
    private TaskDaoImpl<Task> taskDao;
    private Logger logger = Logger.getLogger(TaskManagerImpl.class);
    private ModelMapper mapper;

    public TaskManagerImpl(TaskDaoImpl<Task> taskDao) {
        super(taskDao);
        this.mapper = new ModelMapper();
        this.taskDao = taskDao;
    }


    @Override
    public Object entityToDto(Object entity) {
        return null;
    }

    @Override
    public Object dtoToEntity(Object entity) {
        return null;
    }


}
