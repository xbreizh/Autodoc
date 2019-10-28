package com.autodoc.business.impl.tasks;

import com.autodoc.business.contract.tasks.TemplateSubTaskManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.tasks.TemplateSubTaskDaoImpl;
import com.autodoc.model.dtos.tasks.TemplateSubTaskDTO;
import com.autodoc.model.models.tasks.TemplateSubTask;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class TemplateSubTaskManagerImpl<T, D> extends AbstractGenericManager implements TemplateSubTaskManager {
    private TemplateSubTaskDaoImpl<TemplateSubTask> templateSubTaskDao;
    private static final Logger LOGGER = Logger.getLogger(TemplateSubTaskManagerImpl.class);
    private ModelMapper mapper;

    public TemplateSubTaskManagerImpl(TemplateSubTaskDaoImpl<TemplateSubTask> templateSubTaskDao) {
        super(templateSubTaskDao);
        this.mapper = new ModelMapper();
        this.templateSubTaskDao = templateSubTaskDao;
    }


    @Override
    public TemplateSubTaskDTO entityToDto(Object entity) {
        TemplateSubTaskDTO dto = mapper.map(entity, TemplateSubTaskDTO.class);
        LOGGER.info("converted into dto");
        return dto;
    }

    @Override
    public TemplateSubTask dtoToEntity(Object entity) throws Exception {
        TemplateSubTaskDTO dto = (TemplateSubTaskDTO) entity;
        TemplateSubTask templateSubTask = mapper.map(entity, TemplateSubTask.class);
        checkDataInsert(dto);
        return templateSubTask;
    }


}
