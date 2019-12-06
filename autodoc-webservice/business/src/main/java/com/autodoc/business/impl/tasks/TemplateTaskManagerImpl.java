/*
package com.autodoc.business.impl.tasks;

import com.autodoc.business.contract.tasks.TemplateTaskManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.tasks.TemplateTaskDaoImpl;
import com.autodoc.model.dtos.tasks.TemplateTaskDTO;
import com.autodoc.model.models.tasks.TemplateTask;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class TemplateTaskManagerImpl<T, D> extends AbstractGenericManager implements TemplateTaskManager {
    private TemplateTaskDaoImpl<TemplateTask> templateSubTaskDao;
    private static final Logger LOGGER = Logger.getLogger(TemplateTaskManagerImpl.class);
    private ModelMapper mapper;

    public TemplateTaskManagerImpl(TemplateTaskDaoImpl<TemplateTask> templateSubTaskDao) {
        super(templateSubTaskDao);
        this.mapper = new ModelMapper();
        this.templateSubTaskDao = templateSubTaskDao;
    }


    @Override
    public TemplateTaskDTO entityToDto(Object entity) {
        TemplateTaskDTO dto = mapper.map(entity, TemplateTaskDTO.class);
        LOGGER.info("converted into dto");
        return dto;
    }

    @Override
    public TemplateTask dtoToEntity(Object entity) throws Exception {
        TemplateTaskDTO dto = (TemplateTaskDTO) entity;
        TemplateTask templateTask = mapper.map(entity, TemplateTask.class);
        checkDataInsert(dto);
        return templateTask;
    }


}
*/
