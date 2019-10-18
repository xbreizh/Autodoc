package com.autodoc.business.impl.tasks;

import com.autodoc.business.contract.tasks.TaskManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.dao.impl.tasks.TaskDaoImpl;
import com.autodoc.model.models.tasks.Task;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
public class TaskManagerImpl<T, D> extends AbstractGenericManager implements TaskManager {
    private TaskDaoImpl<Task> taskDao;
    private Logger logger = Logger.getLogger(TaskManagerImpl.class);

    public TaskManagerImpl( TaskDaoImpl<Task> taskDao) {
        super(taskDao);
        this.taskDao = taskDao;
    }

    @Override
    public String save(Task task) {
        taskDao.create(task);
        return "piece added";
    }

    @Override
    public Object entityToDto(Object entity) {
        return null;
    }

  /*  @Override
    public List<Task> getAll() {
        return null;
    }*/
}
