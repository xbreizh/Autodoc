package com.autodoc.business.impl.tasks;

import com.autodoc.business.contract.tasks.TaskManager;
import com.autodoc.dao.contract.tasks.SubTaskDao;
import com.autodoc.dao.contract.tasks.TaskDao;
import com.autodoc.model.dtos.tasks.TaskDTO;
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
    private SubTaskDao subTaskDao;

    @BeforeEach
    void setUp() {
        dao = mock(TaskDao.class);
        subTaskDao = mock(SubTaskDao.class);
        manager = new TaskManagerImpl(dao);
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
        long price = 12345678910L;
        dto.setGlobalPrice(price);
        List<Integer> subTaskList = new ArrayList<>();
        subTaskList.add(id);
        dto.setSubTasks(subTaskList);
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
        long price = 12345678910L;
        dto.setGlobalPrice(price);
        List<Integer> subTaskList = new ArrayList<>();
        subTaskList.add(id);
        dto.setSubTasks(subTaskList);
        when(dao.update(any(Task.class))).thenReturn(true);
        manager.update(dto);
        assertEquals(true, manager.update(dto));
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
        when(dao.deleteById(anyInt())).thenReturn(true);
        when(dao.getById(anyInt())).thenReturn(new Task());
        assertTrue(manager.deleteById(2));
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