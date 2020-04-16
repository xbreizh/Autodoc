package com.autodoc.business.impl.tasks;

import com.autodoc.business.contract.tasks.TaskManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.dao.contract.tasks.TaskDao;
import com.autodoc.model.dtos.tasks.TaskDTO;
import com.autodoc.model.models.tasks.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class TaskManagerImplTest {

    private static final String NAME = "taskName";
    private static final String DTO_NAME = "taskDTo";
    private static final String DESCRIPTION = "descriere";
    private static final double ESTIMATED_TIME = 1.5;
    private TaskManager manager;
    private TaskDao dao;
    private Task obj;
    private TaskDTO dto;
    private int id = 32;

    @BeforeEach
    void setUp() {
        dao = mock(TaskDao.class);
        manager = TaskManagerImpl.builder().dao(dao).build();
        obj = Task.builder().id(id).name(NAME).description(DESCRIPTION).estimatedTime(ESTIMATED_TIME).build();
        dto = TaskDTO.builder().id(24).name(DTO_NAME).description("descrierr").estimatedTime(ESTIMATED_TIME).build();
    }


    @Test
    @DisplayName("should return obj if existing")
    void getDao() {
        assertEquals(dao, manager.getDao());

    }

    @Test
    @DisplayName("returns entity class")
    void getEntityClass() {
        assertEquals(Task.class, manager.getEntityClass());
    }


    @Test
    @DisplayName("returns dto class")
    void getDtoClass() {
        assertEquals(TaskDTO.class, manager.getDtoClass());
    }


    @Test
    @DisplayName("should convert dto into entity")
    void dtoToEntity() {
        obj = (Task) manager.dtoToEntity(dto);
        assertAll(
                () -> assertEquals(obj.getName(), dto.getName().toUpperCase()),
                () -> assertEquals(obj.getDescription(), dto.getDescription().toUpperCase()),
                () -> assertEquals(obj.getEstimatedTime(), dto.getEstimatedTime()),
                () -> assertEquals(obj.getId(), dto.getId())
        );

    }

    @Test
    @DisplayName("should convert entity to dto")
    void entityToDto() {
        dto = (TaskDTO) manager.entityToDto(obj);
        assertAll(
                () -> assertEquals(obj.getName(), dto.getName()),
                () -> assertEquals(obj.getDescription(), dto.getDescription()),
                () -> assertEquals(obj.getEstimatedTime(), dto.getEstimatedTime()),
                () -> assertEquals(obj.getId(), dto.getId())
        );

    }

    @Test
    @DisplayName("should not throw error")
    void transferInsert() {
        obj = (Task) manager.transferInsert(dto);
        assertAll(
                () -> assertEquals(obj.getName(), dto.getName().toUpperCase()),
                () -> assertEquals(obj.getDescription(), dto.getDescription().toUpperCase()),
                () -> assertEquals(obj.getEstimatedTime(), dto.getEstimatedTime()),
                () -> assertEquals(obj.getId(), dto.getId())
        );
    }

    @Test
    @DisplayName("should throw an error if estimated time is < 0")
    void transferInsert1() {
        dto.setEstimatedTime(0);
        assertThrows(InvalidDtoException.class, () -> manager.transferInsert(dto));
    }

    @Test
    @DisplayName("should throw an error if name is a duplicate")
    void checkIfDuplicate() {
        when(dao.getByName(anyString())).thenReturn(obj);
        assertThrows(InvalidDtoException.class, () -> manager.checkIfDuplicate(dto));
    }

    @Test
    @DisplayName("should not throw error")
    void transferUpdate() {
        dto.setId(id);
        when(dao.getById(anyInt())).thenReturn(obj);
        obj = (Task) manager.transferUpdate(dto);
        assertAll(
                () -> assertEquals(obj.getName(), dto.getName().toUpperCase()),
                () -> assertEquals(obj.getDescription(), dto.getDescription().toUpperCase()),
                () -> assertEquals(obj.getEstimatedTime(), dto.getEstimatedTime()),
                () -> assertEquals(obj.getId(), dto.getId())
        );
    }

    @Test
    @DisplayName("should throw an error if no id passed")
    void transferUpdate1() {
        dto.setId(0);
        assertThrows(InvalidDtoException.class, () -> manager.transferUpdate(dto));
    }

    @Test
    @DisplayName("should throw an error if id invalid")
    void transferUpdate2() {
        when(dao.getById(anyInt())).thenReturn(null);
        assertThrows(InvalidDtoException.class, () -> manager.transferUpdate(dto));
    }


    @Test
    void getById() {
        Task task = new Task();
        when(dao.getById(anyInt())).thenReturn(task);
        assertNotNull(manager.getById(2));
    }

    @Test
    @DisplayName("should return task if name valid")
    void getByName() {
        Task task = new Task();
        when(dao.getByName(anyString())).thenReturn(task);
        assertNotNull(manager.getByName("name"));
    }

    @Test
    @DisplayName("should return task if name valid")
    void getByName1() {

        when(dao.getByName(anyString())).thenReturn(null);
        assertThrows(InvalidDtoException.class, () -> manager.getByName("name"));
    }


}
