package com.autodoc.business.impl.tasks;

import com.autodoc.business.contract.tasks.TaskManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.contract.pieces.PieceDao;
import com.autodoc.dao.contract.tasks.TaskDao;
import com.autodoc.model.dtos.tasks.TaskDTO;
import com.autodoc.model.models.tasks.Task;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class TaskManagerImpl<T, D> extends AbstractGenericManager implements TaskManager {
    private static final Logger LOGGER = Logger.getLogger(TaskManagerImpl.class);
    private TaskDao taskDao;
    private ModelMapper mapper;
    private PieceDao pieceDao;


    public TaskManagerImpl(TaskDao taskDao, PieceDao pieceDao) {
        super(taskDao);
        this.mapper = new ModelMapper();
        this.taskDao = taskDao;
        this.pieceDao = pieceDao;
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
     //   dto.setPrice(task.getPrice());
        dto.setEstimatedTime(task.getEstimatedTime());
       // dto.setTemplate(Boolean.toString(task.isTemplate()));
        List<Integer> pieceIds = new ArrayList<>();
        LOGGER.info("converting pieces");
      /*  if (task.getPieces()!=null){
            for (Piece piece: task.getPieces()){
                pieceIds.add(piece.getId());
            }
            dto.setPieces(pieceIds);
        }*/
        LOGGER.info("converted into dto: " + dto);
        return dto;
    }

    @Override
    public Task dtoToEntity(Object entity) throws InvalidDtoException {
        LOGGER.info("trying to convert into entity");
        TaskDTO dto = (TaskDTO) entity;
        Task task = new Task();

        //checkDataInsert(dto);
        return task;
    }

    @Override
    public boolean deleteById(int id) throws Exception {
        LOGGER.info("trying to delete by id");
        Task task = (Task) taskDao.getById(id);
        if (task == null) throw new Exception("id is invalid: " + id);
        /*if (task.isTemplate())
            throw new Exception("You must use the specific deleteTemplate method for deleting templates");*/
        return taskDao.deleteById(id);
    }


 /*   @Override
    public boolean deleteTemplateById(int id) throws Exception {
        Task task = (Task) taskDao.getById(id);
        if (task == null) throw new Exception("id is invalid: " + id);
        if (!task.isTemplate()) throw new Exception("You must use the general delete method for deleting regular task");
        return taskDao.deleteById(id);

    }

    @Override
    public Task createFromTemplate(int id) throws Exception {
        Task templateTask = (Task) taskDao.getById(id);
        if (templateTask == null) throw new Exception("id is invalid: " + id);
        if (!templateTask.isTemplate()) throw new Exception("not a template id: " + id);
        Task task = new Task();
        task.setName(templateTask.getName());
        task.setDescription(templateTask.getDescription());
        task.setEstimatedTime(templateTask.getEstimatedTime());
        task.setTemplate(false);
        int taskId = taskDao.create(task);
        Task newTask = (Task) taskDao.getById(taskId);
        return newTask;

    }*/


    public Task transferInsert(Object obj) throws InvalidDtoException {
        LOGGER.info("trying to convert into entity");
        TaskDTO dto = (TaskDTO) obj;
        String name = dto.getName();
        //double price = dto.getPrice();
        double estimatedTime = dto.getEstimatedTime();
        Task task = (Task) taskDao.getByName(name);
        if (task != null) throw new InvalidDtoException("there is already a task with that name");
        task = new Task();
        //if (price == 0) throw new InvalidDtoException("there should be a price");
        if (estimatedTime == 0) throw new InvalidDtoException("there should be an estimated time");
        task.setName(dto.getName().toUpperCase());
        //  transferTemplateToEntity(dto, task);
        task.setDescription(dto.getDescription());
        task.setEstimatedTime(dto.getEstimatedTime());
      //  task.setPrice(dto.getPrice());
       // updatePieces(dto, task);
        LOGGER.info("task so far: " + task);
        return task;
    }

   /* private void transferTemplateToEntity(TaskDTO dto, Task task) throws InvalidDtoException {
        String template = dto.getTemplate();

        if (template != null) {
            if (!template.equalsIgnoreCase("true") && !template.equalsIgnoreCase("false")) {
                throw new InvalidDtoException("template must be a boolean");
            }
            task.setTemplate(Boolean.parseBoolean(template));
            LOGGER.info("passing template value: " + task);
        }
    }*/


   /* private void updatePieces(TaskDTO dto, Task task) throws InvalidDtoException {
        LOGGER.info("updating pieces: " + dto);
        LOGGER.info("updating pieces: " + task);
        if (dto.getPieces() != null) {
            List<Piece> pieceList = new ArrayList<>();
            LOGGER.info("there are pieces");

            for (Integer pieceId : dto.getPieces()) {
                Piece piece = (Piece) pieceDao.getById(pieceId.intValue());
                if (piece == null) {
                    LOGGER.info("invalid piece: " + pieceId);

                    throw new InvalidDtoException("invalid piece: " + pieceId);
            }
                Piece piece1 = (Piece) pieceDao.getById(pieceId);
                piece1.getTasks().add(task);
                pieceDao.update(piece1);
                pieceList.add(piece);
            }

            task.setPieces(pieceList);
        }
        LOGGER.info("updating pieces: " + task);
    }*/


    public Task transferUpdate(Object obj) throws InvalidDtoException {
        TaskDTO dto = (TaskDTO) obj;
        LOGGER.info("trying to convert into entity: " + dto);
        String name = dto.getName();
        //   double price = dto.getPrice();
        double estimatedTime = dto.getEstimatedTime();
        String description = dto.getDescription();
        // String template = dto.getTemplate();

        int id = dto.getId();
        if (id == 0) throw new InvalidDtoException("no id provided");
        Task task = (Task) taskDao.getById(id);
        if (task == null) throw new InvalidDtoException("invalid id " + id);
        LOGGER.info("task to update: " + task);
       /* if (task.isTemplate())
            throw new InvalidDtoException("you must use the updateTemplate method for updating templates");*/
        //transferTemplateToEntity(dto, task);
        if (name != null) task.setName(name.toUpperCase());
        //  if (price != 0) task.setPrice(price);
        if (estimatedTime != 0) task.setEstimatedTime(estimatedTime);
        if (description != null) task.setDescription(description);
        LOGGER.info("here: " + task);
        //    updatePieces(dto, task);

        return task;
    }


  /*  @Override
    public List<TaskDTO> getTemplates() {
        List<TaskDTO> templateList = new ArrayList<>();
        List<TaskDTO> globalList = (List<TaskDTO>) getAll();
        if (globalList.isEmpty()) return templateList;
        for (TaskDTO task : globalList) {
            if (task.getTemplate().equalsIgnoreCase("true")) templateList.add(task);
        }
        return templateList;
    }

    @Override
    public boolean updateTemplate(TaskDTO dto) throws Exception {
        LOGGER.info("trying to convert into entity");
        String name = dto.getName();
       // double price = dto.getPrice();
        double estimatedTime = dto.getEstimatedTime();
        String description = dto.getDescription();
        int id = dto.getId();
        if (id == 0) throw new Exception("no id provided");
        Task task = (Task) taskDao.getById(id);
        if (task == null) throw new Exception("invalid id: " + id);
        LOGGER.info("task from db: "+task);
        if (!task.isTemplate()) throw new Exception("this is not a template. Please use the regular update method");

        if (name != null) task.setName(name.toUpperCase());
        if (description != null) task.setDescription(description);
        if (estimatedTime != 0) task.setEstimatedTime(estimatedTime);
      //  if (price != 0) task.setPrice(price);
        transferTemplateToEntity(dto, task);
       // updatePieces(dto, task);

        return taskDao.update(task);
    }*/
}
