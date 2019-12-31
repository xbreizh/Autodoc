/*
package com.autodoc.dao.impl.tasks;

import com.autodoc.dao.contract.tasks.SubTaskDao;
import com.autodoc.model.models.tasks.SubTask;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
//@Sql(scripts = "classpath:resetDb_scripts/resetDbCar.sql")
@Transactional
class SubTaskDaoImplTest {

    @Inject
    private SubTaskDao subTaskDao;

    @Test
    void create() {
        SubTask subTask = new SubTask();
        subTask.setId(222);
        subTask.setName("derf");
        LOGGER.info(subTask);
        subTaskDao.create(subTask);
        LOGGER.info(subTaskDao.getAll());
    }
}*/
