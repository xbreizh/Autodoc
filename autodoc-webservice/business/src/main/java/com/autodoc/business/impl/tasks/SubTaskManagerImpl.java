package com.autodoc.business.impl.tasks;

import com.autodoc.business.contract.tasks.SubTaskManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.tasks.SubTaskDaoImpl;
import com.autodoc.model.dtos.tasks.SubTaskDTO;
import com.autodoc.model.models.tasks.SubTask;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class SubTaskManagerImpl<T, D> extends AbstractGenericManager implements SubTaskManager {
    private static final Logger LOGGER = Logger.getLogger(SubTaskManagerImpl.class);
    private ModelMapper mapper;

    public SubTaskManagerImpl(SubTaskDaoImpl<SubTask> subTaskDao) {
        super(subTaskDao);
        this.mapper = new ModelMapper();
    }


    @Override
    public SubTaskDTO entityToDto(Object entity) {
        SubTaskDTO dto = mapper.map(entity, SubTaskDTO.class);
        LOGGER.info("converted into dto");
        return dto;
    }

    @Override
    public SubTask dtoToEntity(Object entity) throws Exception {
        SubTaskDTO dto = (SubTaskDTO) entity;
        SubTask subTask = mapper.map(entity, SubTask.class);
        checkDataInsert(dto);
        return subTask;
    }

}
