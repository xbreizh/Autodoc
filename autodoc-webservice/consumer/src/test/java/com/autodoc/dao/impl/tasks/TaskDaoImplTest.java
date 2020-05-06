package com.autodoc.dao.impl.tasks;

import com.autodoc.dao.contract.tasks.TaskDao;
import com.autodoc.dao.filler.Filler;
import com.autodoc.dao.filler.Remover;
import com.autodoc.model.models.tasks.Task;
import org.apache.log4j.BasicConfigurator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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


    Task obj;
    @Inject
    private TaskDao dao;
    @Inject
    private Filler filler;
    @Inject
    private Remover remover;

    @BeforeEach
    void init()  throws Exception {
        BasicConfigurator.configure();
        //remover.remove();
        filler.fill();
        obj = (Task) dao.getAll().get(0);
    }


    @Test
    void trrr() {
        int i = 5;
        double h = Double.valueOf(i);
        System.out.println("h:" + h);
    }

    @Test
    @DisplayName("should return null if invalid name")
    void findByName() {
        assertNull(dao.getByName("invalidName"));
    }

    @Test
    @DisplayName("should return object if valid name")
    void findByName1() {

        assertEquals(obj, dao.getByName(obj.getName()));
    }

    @Test
    @DisplayName("should return search fields")
    void getSearchField() {
        assertEquals(Task.getSearchField(), dao.getSearchField());
    }

    @Test
    void deleteById() {
        int id = obj.getId();
        assertAll(
                () -> assertNotNull(dao.getById(id)),
                () -> assertTrue(dao.deleteById(id)),
                () -> assertNull(dao.getById(id))
        );
    }

    @Test
    void testDeleteById() {
        obj = (Task) dao.getById(1);
        dao.deleteById(2);
    }
}
