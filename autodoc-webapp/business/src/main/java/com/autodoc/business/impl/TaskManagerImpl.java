package com.autodoc.business.impl;


import com.autodoc.business.contract.PieceManager;
import com.autodoc.business.contract.TaskManager;
import com.autodoc.contract.TaskService;
import com.autodoc.model.dtos.tasks.TaskDTO;
import com.autodoc.model.dtos.tasks.TaskForm;
import com.autodoc.model.models.pieces.Piece;
import com.autodoc.model.models.tasks.Task;
import org.apache.log4j.Logger;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class TaskManagerImpl extends GlobalManagerImpl<Task, TaskDTO> implements TaskManager {

    private static final Logger LOGGER = Logger.getLogger(TaskManagerImpl.class);
    public Task task;
    private TaskService service;
    private  PieceManager pieceManager;

    public TaskManagerImpl(TaskService service, PieceManager pieceManager) {
        super(service);
        this.service = service;
        this.pieceManager = pieceManager;
    }

    public Task dtoToEntity(String token, Object obj) throws Exception {

        TaskDTO dto = (TaskDTO) obj;
        LOGGER.info("dto: " + dto);
        Task task = new Task();
        int id = dto.getId();
        LOGGER.info("id: " + id);
        task.setId(id);
        task.setName(dto.getName());
        task.setDescription(dto.getDescription());
        task.setEstimatedTime(dto.getEstimatedTime());
        task.setPrice(dto.getPrice());
        String templateValue = dto.getTemplate();
        List<Piece> pieces = new ArrayList<>();
        for (Integer pieceId: dto.getPieces()){
            Piece piece = (Piece) pieceManager.getById(token, pieceId);
            pieces.add(piece);
        }
        task.setPieces(pieces);
        if (templateValue.equalsIgnoreCase("true") || templateValue.equalsIgnoreCase("false"))
            task.setTemplate(Boolean.valueOf(templateValue));
        LOGGER.info("task transferred: " + task);

        return task;
    }

    public TaskDTO formToDto(Object obj, String token) {
        LOGGER.info("stuff to update: " + obj);
        TaskForm form = (TaskForm) obj;
        LOGGER.info("dto: " + form);
        TaskDTO dto = new TaskDTO();
        dto.setId(form.getId());
        dto.setName(form.getName());
        dto.setDescription(form.getDescription());
        dto.setEstimatedTime(form.getEstimatedTime());
        dto.setPrice(form.getPrice());
        String templateValue = String.valueOf(form.isTemplate());
        if (templateValue.equalsIgnoreCase("true") || templateValue.equalsIgnoreCase("false"))
            dto.setTemplate(templateValue);
       dto.setPieces(form.getPieces());
        LOGGER.info("task transferred: " + dto);
        return dto;
    }


}
