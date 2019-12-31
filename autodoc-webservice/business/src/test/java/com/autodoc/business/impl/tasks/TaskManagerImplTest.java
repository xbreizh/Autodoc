package com.autodoc.business.impl.tasks;

import com.autodoc.business.contract.tasks.TaskManager;
import com.autodoc.dao.contract.pieces.PieceDao;
import com.autodoc.dao.contract.tasks.TaskDao;
import com.autodoc.model.dtos.tasks.TaskDTO;
import com.autodoc.model.models.pieces.Piece;
import com.autodoc.model.models.tasks.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
@Transactional
class TaskManagerImplTest {

    private TaskManager manager;
    private TaskDao dao;
    private Task task;
    private PieceDao pieceDao;


    @BeforeEach
    void setUp() {
        dao = mock(TaskDao.class);
        pieceDao = mock(PieceDao.class);
        manager = new TaskManagerImpl(dao, pieceDao);
        task = new Task();
    }

    @Test
    void resetException() {
    }

    @Test
    void save() throws Exception {
        TaskDTO dto = new TaskDTO();
        String name = "tache";
        int id = 34;
        dto.setName(name);
        double price = 12345678910L;
        dto.setPrice(price);
        List<Integer> subTaskList = new ArrayList<>();
        subTaskList.add(id);
        when(dao.create(any(Task.class))).thenReturn(id);
        manager.save(dto);
        assertEquals(Integer.toString(id), manager.save(dto));
    }

    @Test
    void transferInsert() {
    }

    @Test
    void update() throws Exception {
        TaskDTO dto = new TaskDTO();
        String name = "tache";
        int id = 34;
        dto.setName(name);
        double price = 12345678910L;
        dto.setPrice(price);
        List<Integer> subTaskList = new ArrayList<>();
        subTaskList.add(id);
        when(dao.update(any(Task.class))).thenReturn(true);
        manager.update(dto);
        assertEquals(true, manager.update(dto));
    }

    @Test
    void updateTemplate() throws Exception {
        TaskDTO dto = new TaskDTO();
        String name = "tache";
        int id = 34;
        dto.setName(name);
        dto.setId(id);
        double price = 12345678910L;
        dto.setPrice(price);
        List<Integer> subTaskList = new ArrayList<>();
        subTaskList.add(id);
        when(dao.getById(anyInt())).thenReturn(task);
        when(pieceDao.getById(anyInt())).thenReturn(null);
        assertEquals(true, manager.updateTemplate(dto));
    }


    @Test
    void transferUpdate() {
    }

    @Test
    void checkDataInsert() {
    }

    @Test
    void checkDataUpdate() {
    }

    @Test
    void getById() throws Exception {
        Task task = new Task();
        when(dao.getById(anyInt())).thenReturn(task);
        assertNotNull(manager.getById(2));
    }

    @Test
    @DisplayName("should return task if name valid")
    void getByName() throws Exception {
        Task task = new Task();
        when(dao.getByName(anyString())).thenReturn(task);
        assertNotNull(manager.getByName("name"));
    }

    @Test
    @DisplayName("should return task if name valid")
    void getByName1() throws Exception {
        Task task = new Task();
        when(dao.getByName(anyString())).thenReturn(null);
        assertNull(manager.getByName("name"));
    }

    @Test
    void getAll() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task());
        taskList.add(new Task());
        when(dao.getAll()).thenReturn(taskList);
        assertAll(
                () -> assertNotNull(manager.getAll()),
                () -> assertEquals(2, manager.getAll().size())
        );
    }

    @Test
    void convertList() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() throws Exception {
        List<Piece> pieceList = new ArrayList<>();
        pieceList.add(new Piece());
        task.setPieces(pieceList);
        task.setTemplate(false);
        when(dao.deleteById(anyInt())).thenReturn(true);
        when(dao.getById(anyInt())).thenReturn(task);
        assertTrue(manager.deleteById(2));
    }

    @Test
    void deleteTemplateById() throws Exception {
        List<Piece> pieceList = new ArrayList<>();
        pieceList.add(new Piece());
        task.setPieces(pieceList);
        task.setTemplate(true);
        when(dao.deleteById(anyInt())).thenReturn(true);
        when(dao.getById(anyInt())).thenReturn(task);
        assertTrue(manager.deleteTemplateById(2));
    }

    @Test
    void searchByCriteria() {
    }

    @Test
    void isCompareCriteria() {
    }

    @Test
    void entityToDto() {
    }

    @Test
    void dtoToEntity() {
    }
}