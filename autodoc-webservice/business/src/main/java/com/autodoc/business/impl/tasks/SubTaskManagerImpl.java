package com.autodoc.business.impl.tasks;

import com.autodoc.business.contract.tasks.SubTaskManager;
import com.autodoc.dao.impl.tasks.SubTaskDaoImpl;
import com.autodoc.model.models.tasks.SubTask;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
public class SubTaskManagerImpl implements SubTaskManager {
    private SubTaskDaoImpl<SubTask> subTaskDao;
    private Logger logger = Logger.getLogger(SubTaskManagerImpl.class);


    public SubTaskManagerImpl(SubTaskDaoImpl<SubTask> subTaskDao) {
        this.subTaskDao = subTaskDao;

    }


    @Override
    public String save(SubTask subTask) {
        subTaskDao.create(subTask);
        return "piece added";
    }

    @Override
    public List<SubTask> getAll() {
        return null;
    }
}
