package com.autodoc.business.impl.tasks;

import com.autodoc.business.contract.tasks.TaskManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.dao.contract.tasks.TaskDao;
import com.autodoc.model.dtos.tasks.TaskDTO;
import com.autodoc.model.models.tasks.Task;
import lombok.Builder;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
@Builder
@SuppressWarnings("unchecked")
public class TaskManagerImpl extends AbstractGenericManager implements TaskManager {
    private static final Logger LOGGER = Logger.getLogger(TaskManagerImpl.class);
    private static final ModelMapper mapper = new ModelMapper();
    private TaskDao dao;

    public Class getEntityClass() {
        return Task.class;
    }

    public Class getDtoClass() {
        return TaskDTO.class;
    }

    @Override
    public IGenericDao getDao() {
        LOGGER.info("getting dao: ");

        return dao;
    }


    public Task transferInsert(Object obj) {
        TaskDTO dto = (TaskDTO) obj;
        Task task = (Task) dtoToEntity(dto);
        checkIfDuplicate(dto);
        if (task.getEstimatedTime() == 0) throw new InvalidDtoException("there should be " +
                "an estimated time");
        LOGGER.info("task so far: " + task);
        return task;
    }

    public void checkIfDuplicate(Object obj) {
        TaskDTO dto = (TaskDTO) obj;
        if (dao.getByName(dto.getName()) != null)
            throw new InvalidDtoException("there is already a task with that name");
    }


    public Task transferUpdate(Object obj) {
        TaskDTO dto = (TaskDTO) obj;
        int id = dto.getId();
        if (id == 0) throw new InvalidDtoException("no id provided");
        Task task = (Task) dao.getById(id);
        if (task == null) throw new InvalidDtoException("invalid id " + id);
        if(dto.getName()!=null)task.setName(dto.getName());
        if(dto.getDescription()!=null)task.setDescription(dto.getDescription());
        if(dto.getEstimatedTime()!=0)task.setEstimatedTime(dto.getEstimatedTime());
        return task;
    }


}
