package com.autodoc.dao.impl.tasks;

import com.autodoc.dao.contract.tasks.TaskDao;
import com.autodoc.dao.filler.Filler;
import com.autodoc.model.models.tasks.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
@Transactional
class TaskDaoImplTest {

    int id = 0;
    Task task;
    @Inject
    private TaskDao dao;
    @Inject
    private Filler filler;

    @BeforeEach
    void init() throws Exception {
        // filler.fill();
        //task = (Task) dao.getAll().get(0);
        //id = task.getId();
    }

    @Test
    void getAll() {
        assertEquals(3, dao.getAll().size());
    }

    @Test
    void getByName() {
        String name = task.getName();
        System.out.println(dao.getAll());
        assertNotNull(dao.getByName(name));
    }


    @Test
    void create() {
        Task task = new Task();
        task.setId(222);
        task.setName("derf");
        assertEquals(0, dao.getAll().size());
        dao.create(task);
        assertEquals(1, dao.getAll().size());
    }

    @Test
    void update() {
        Task task = (Task) dao.getById(id);
        String name = "testName";
        assertNotEquals(name, task.getName());
        task.setName(name);
        dao.update(task);
        assertEquals(name, ((Task) dao.getById(id)).getName());
    }

    @Test
    void deleteById() {
        Task task = (Task) dao.getAll().get(0);
        task = (Task) dao.getById(1);
        int id = task.getId();
        assertAll(
                () -> assertNotNull(dao.getById(id)),
                () -> assertTrue(dao.deleteById(id)),
                () -> assertNull(dao.getById(id))
        );
    }

    @Test
    void getSearchField() {
    }

    @Test
    void testDeleteById() {
        task = (Task) dao.getById(1);
        dao.deleteById(2);
    }
}