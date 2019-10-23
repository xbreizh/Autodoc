package com.autodoc.business.impl.tasks;

import com.autodoc.business.contract.tasks.SubTaskManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.tasks.SubTaskDaoImpl;
import com.autodoc.model.models.tasks.SubTask;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class SubTaskManagerImpl<T, D> extends AbstractGenericManager implements SubTaskManager {
    private Logger logger = Logger.getLogger(SubTaskManagerImpl.class);
    private ModelMapper mapper;

    public SubTaskManagerImpl(SubTaskDaoImpl<SubTask> subTaskDao) {
        super(subTaskDao);
        this.mapper = new ModelMapper();
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
