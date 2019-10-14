package com.autodoc.business.impl.tasks;

import com.autodoc.business.contract.tasks.TemplateSubTaskManager;
import com.autodoc.dao.impl.tasks.TemplateSubTaskDaoImpl;
import com.autodoc.model.models.tasks.TemplateSubTask;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
public class TemplateSubTaskManagerImpl implements TemplateSubTaskManager {
    private TemplateSubTaskDaoImpl<TemplateSubTask> templateSubTaskDao;
    private Logger logger = Logger.getLogger(TemplateSubTaskManagerImpl.class);


    public TemplateSubTaskManagerImpl(TemplateSubTaskDaoImpl<TemplateSubTask> templateSubTaskDao) {
        this.templateSubTaskDao = templateSubTaskDao;

    }


    @Override
    public String save(TemplateSubTask templateSubTask) {
        templateSubTaskDao.create(templateSubTask);
        return "piece added";
    }

    @Override
    public List<TemplateSubTask> getAll() {
        return null;
    }
}
