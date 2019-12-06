package com.autodoc.business.impl.tasks;

import com.autodoc.business.contract.pieces.PieceManager;
import com.autodoc.business.contract.tasks.TaskManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.contract.tasks.TaskDao;
import com.autodoc.model.dtos.tasks.TaskDTO;
import com.autodoc.model.models.pieces.Piece;
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
    private PieceManager pieceManager;

    public TaskManagerImpl(TaskDao taskDao, PieceManager pieceManager) {
        super(taskDao);
        this.mapper = new ModelMapper();
        this.taskDao = taskDao;
        this.pieceManager = pieceManager;
    }


    @Override
    public TaskDTO entityToDto(Object entity) {
        Task task = (Task) entity;
        TaskDTO dto = new TaskDTO();
        LOGGER.info("converted into dto: " + dto);
        dto.setId(task.getId());
        dto.setName(task.getName());
        dto.setDescription(task.getDescription());
        dto.setPrice(task.getPrice());
        dto.setEstimatedTime(task.getEstimatedTime());
        dto.setTemplate(Boolean.toString(task.isTemplate()));
        List<Integer> pieceIds = new ArrayList<>();
       /* if (task.getPieces()!=null){
            for (Piece piece: task.getPieces()){
                pieceIds.add(piece.getId());
            }
            dto.setPieces(pieceIds);
        }*/
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

    @Override
    public boolean deleteById(int id) throws Exception {
        System.out.println("trying to delete by id");
        Task task = (Task) taskDao.getById(id);
        if (task == null) throw new Exception("id is invalid: " + id);
        if (task.isTemplate())
            throw new Exception("You must use the specific deleteTemplate method for deleting templates");
        return taskDao.deleteById(id);
    }


    @Override
    public boolean deleteTemplateById(int id) throws Exception {
        Task task = (Task) taskDao.getById(id);
        if (task == null) throw new Exception("id is invalid: " + id);
        if (!task.isTemplate()) throw new Exception("You must use the general delete method for deleting regular task");
        return taskDao.deleteById(id);

    }

    @Override
    public String createFromTemplate(int id) throws Exception {
        Task templateTask = (Task) taskDao.getById(id);
        if (templateTask == null) throw new Exception("id is invalid: " + id);
        if (!templateTask.isTemplate()) throw new Exception("not a template id: " + id);
        Task task = new Task();
        task.setName(templateTask.getName());
        task.setDescription(templateTask.getDescription());
        task.setEstimatedTime(templateTask.getEstimatedTime());
        task.setPrice(templateTask.getPrice());
        task.setPieces(templateTask.getPieces());
        List<Piece> pieces = new ArrayList<>();
        for (Piece piece : templateTask.getPieces()) {
            pieces.add(piece);
        }
        task.setPieces(pieces);
        task.setTemplate(false);
        return Integer.toString(taskDao.create(task));

    }


    public Task transferInsert(Object obj) throws Exception {
        System.out.println("trying to convert into entity");
        TaskDTO dto = (TaskDTO) obj;
        String name = dto.getName();
        double price = dto.getPrice();
        int estimatedTime = dto.getEstimatedTime();
        Task task = (Task) taskDao.getByName(name);
        if (task != null) throw new Exception("there is already a task with that name");
        task = new Task();
        if (price == 0) throw new Exception("there should be a price");
        if (estimatedTime == 0) throw new Exception("there should be an estimated time");
        task.setName(dto.getName().toUpperCase());
        transferTemplateToEntity(dto, task);

        task.setPrice(dto.getPrice());
        updatePieces(dto, task);
        return task;
    }

    private void transferTemplateToEntity(TaskDTO dto, Task task) throws Exception {
        String template = dto.getTemplate();
        if (template != null) {
            System.out.println();
            if (!template.equalsIgnoreCase("true") && !template.equalsIgnoreCase("false")) {
                throw new Exception("template must be a boolean");
            }
            task.setTemplate(Boolean.parseBoolean(template));
            System.out.println("passing template value: " + task);
        }
    }

    private void updatePieces(TaskDTO dto, Task task) throws Exception {
        if (dto.getPieces() != null) {
            List<Piece> pieceList = new ArrayList<>();
            for (Integer pieceId : dto.getPieces()) {
                Piece piece = (Piece) pieceManager.getById(pieceId);
                if (piece == null) throw new Exception("invalid piece: " + pieceId);
                pieceList.add(piece);
            }
            // task.setPieces(pieceList);
        }
    }


    public Task transferUpdate(Object obj) throws Exception {
        TaskDTO dto = (TaskDTO) obj;
        System.out.println("trying to convert into entity: " + dto);
        String name = dto.getName();
        double price = dto.getPrice();
        int estimatedTime = dto.getEstimatedTime();
        String description = dto.getDescription();
        String template = dto.getTemplate();

        int id = dto.getId();
        if (id == 0) throw new Exception("no id provided");
        Task task = (Task) taskDao.getById(id);
        if (task == null) throw new Exception("invalid id " + id);
        if (task.isTemplate()) throw new Exception("you must use the updateTemplate method for updating templates");
        transferTemplateToEntity(dto, task);
        if (name != null) task.setName(name.toUpperCase());
        if (price != 0) task.setPrice(price);
        if (estimatedTime != 0) task.setEstimatedTime(estimatedTime);
        if (description != null) task.setDescription(description);
        updatePieces(dto, task);

        return task;
    }


    @Override
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
        System.out.println("trying to convert into entity");
        String name = dto.getName();
        double price = dto.getPrice();
        int estimatedTime = dto.getEstimatedTime();
        String description = dto.getDescription();
        int id = dto.getId();
        if (id == 0) throw new Exception("no id provided");
        Task task = (Task) taskDao.getById(id);
        if (task == null) throw new Exception("invalid id: " + id);
        if (!task.isTemplate()) throw new Exception("this is not a template. Please use the regular update method");

        if (name != null) task.setName(name.toUpperCase());
        if (description != null) task.setDescription(description);
        if (estimatedTime != 0) task.setEstimatedTime(estimatedTime);
        if (price != 0) task.setPrice(price);
        transferTemplateToEntity(dto, task);
        updatePieces(dto, task);

        return taskDao.update(task);
    }
}
