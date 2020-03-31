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

import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
@Builder
public class TaskManagerImpl<T, D> extends AbstractGenericManager implements TaskManager {
    private static final Logger LOGGER = Logger.getLogger(TaskManagerImpl.class);
    private static final ModelMapper mapper = new ModelMapper();
    private TaskDao dao;

    @Override
    public IGenericDao getDao() {
        LOGGER.info("getting dao: ");

        return dao;
    }


    @Override
    public TaskDTO entityToDto(Object entity) {
        Task task = (Task) entity;
        TaskDTO dto = new TaskDTO();
        LOGGER.info("converted into dto: " + dto);
        LOGGER.info("entity to transfer: " + task);
        dto.setId(task.getId());
        dto.setName(task.getName());
        dto.setDescription(task.getDescription());
        dto.setEstimatedTime(task.getEstimatedTime());
        List<Integer> pieceIds = new ArrayList<>();
        LOGGER.info("converting pieces");
        LOGGER.info("converted into dto: " + dto);
        return dto;
    }

    @Override
    public Task dtoToEntity(Object entity) throws InvalidDtoException {
        LOGGER.info("trying to convert into entity");
        TaskDTO dto = (TaskDTO) entity;
        Task task = Task.builder().build();
        return task;
    }

    @Override
    public boolean deleteById(int id) throws Exception {
        LOGGER.info("trying to delete by id");
        Task task = (Task) dao.getById(id);
        if (task == null) throw new Exception("id is invalid: " + id);
        return dao.deleteById(id);
    }




    public Task transferInsert(Object obj) throws InvalidDtoException {
        LOGGER.info("trying to convert into entity");
        TaskDTO dto = (TaskDTO) obj;
        String name = dto.getName();
        double estimatedTime = dto.getEstimatedTime();
        Task task = (Task) dao.getByName(name);
        if (task != null) throw new InvalidDtoException("there is already a task with that name");
        task = Task.builder().build();
        if (estimatedTime == 0) throw new InvalidDtoException("there should be an estimated time");
        task.setName(dto.getName().toUpperCase());
        task.setDescription(dto.getDescription());
        task.setEstimatedTime(dto.getEstimatedTime());
        LOGGER.info("task so far: " + task);
        return task;
    }



    public Task transferUpdate(Object obj) throws InvalidDtoException {
        TaskDTO dto = (TaskDTO) obj;
        LOGGER.info("trying to convert into entity: " + dto);
        String name = dto.getName();
        double estimatedTime = dto.getEstimatedTime();
        String description = dto.getDescription();

        int id = dto.getId();
        if (id == 0) throw new InvalidDtoException("no id provided");
        Task task = (Task) dao.getById(id);
        if (task == null) throw new InvalidDtoException("invalid id " + id);
        LOGGER.info("task to update: " + task);
        if (name != null) task.setName(name.toUpperCase());
        if (estimatedTime != 0) task.setEstimatedTime(estimatedTime);
        if (description != null) task.setDescription(description);
        LOGGER.info("here: " + task);

        return task;
    }


}
