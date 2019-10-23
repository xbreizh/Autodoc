package com.autodoc.business.impl.tasks;

import com.autodoc.business.contract.tasks.TemplateSubTaskManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.tasks.TemplateSubTaskDaoImpl;
import com.autodoc.model.models.tasks.TemplateSubTask;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class TemplateSubTaskManagerImpl<T, D> extends AbstractGenericManager implements TemplateSubTaskManager {
    private TemplateSubTaskDaoImpl<TemplateSubTask> templateSubTaskDao;
    private Logger logger = Logger.getLogger(TemplateSubTaskManagerImpl.class);
    private ModelMapper mapper;

    public TemplateSubTaskManagerImpl(TemplateSubTaskDaoImpl<TemplateSubTask> templateSubTaskDao) {
        super(templateSubTaskDao);
        this.mapper = new ModelMapper();
        this.templateSubTaskDao = templateSubTaskDao;
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
