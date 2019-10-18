package com.autodoc.business.impl.tasks;

import com.autodoc.business.contract.tasks.TemplateSubTaskManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.tasks.TemplateSubTaskDaoImpl;
import com.autodoc.model.models.tasks.TemplateSubTask;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class TemplateSubTaskManagerImpl<T, D> extends AbstractGenericManager implements TemplateSubTaskManager {
    private TemplateSubTaskDaoImpl<TemplateSubTask> templateSubTaskDao;
    private Logger logger = Logger.getLogger(TemplateSubTaskManagerImpl.class);

    public TemplateSubTaskManagerImpl(TemplateSubTaskDaoImpl<TemplateSubTask> templateSubTaskDao) {
        super(templateSubTaskDao);
        this.templateSubTaskDao = templateSubTaskDao;
    }

    @Override
    public String save(TemplateSubTask templateSubTask) {
        templateSubTaskDao.create(templateSubTask);
        return "piece added";
    }

    @Override
    public Object entityToDto(Object entity) {
        return null;
    }

   /* @Override
    public List<TemplateSubTask> getAll() {
        return null;
    }*/
}
