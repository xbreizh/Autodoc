package com.autodoc.business.impl.tasks;

import com.autodoc.business.contract.tasks.TaskManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.contract.tasks.TaskDao;
import com.autodoc.model.dtos.tasks.TaskDTO;
import com.autodoc.model.models.tasks.Task;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class TaskManagerImpl<T, D> extends AbstractGenericManager implements TaskManager {
    private static final Logger LOGGER = Logger.getLogger(TaskManagerImpl.class);
    private TaskDao taskDao;
    private ModelMapper mapper;

    public TaskManagerImpl(TaskDao taskDao) {
        super(taskDao);
        this.mapper = new ModelMapper();
        this.taskDao = taskDao;
    }


    @Override
    public TaskDTO entityToDto(Object entity) {
        Task task = (Task) entity;
        TaskDTO dto = new TaskDTO();
        dto.setName(task.getName());
        dto.setGlobalPrice(task.getGlobalPrice());
        dto.setId(task.getId());
        LOGGER.info("converted into dto: " + dto);
        System.out.println("converted into dto: " + dto);
        return dto;
    }

    @Override
    public Task dtoToEntity(Object entity) throws Exception {
        System.out.println("trying to convert into entity");
        TaskDTO dto = (TaskDTO) entity;
        Task task = new Task();

        //checkDataInsert(dto);
        return task;
    }

    public Task transferInsert(Object obj) throws Exception {
        System.out.println("trying to convert into entity");
        TaskDTO dto = (TaskDTO) obj;
        String name = dto.getName();
        long price = dto.getGlobalPrice();
        Task task = (Task) taskDao.getByName(name);
        if (task != null) throw new Exception("there is already a task with that name");
        task = new Task();
        if (price == 0) throw new Exception("there should be a price");
        task.setName(dto.getName().toUpperCase());
        task.setGlobalPrice(dto.getGlobalPrice());
        return task;
    }


    public Task transferUpdate(Object obj) throws Exception {
        System.out.println("trying to convert into entity");
        TaskDTO dto = (TaskDTO) obj;
        String name = dto.getName();
        long price = dto.getGlobalPrice();
        int id = dto.getId();
        if (id == 0) throw new Exception("no id provided");
        Task task = (Task) taskDao.getById(id);
        if (task == null) throw new Exception("invalid id " + id);
        if (name != null) task.setName(name.toUpperCase());
        if (price != 0) task.setGlobalPrice(price);

        return task;
    }


}
