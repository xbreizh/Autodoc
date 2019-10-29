package com.autodoc.dao.impl.tasks;

import com.autodoc.dao.contract.car.CarDao;
import com.autodoc.dao.contract.tasks.SubTaskDao;
import com.autodoc.dao.contract.tasks.TemplateSubTaskDao;
import com.autodoc.model.models.pieces.Piece;
import com.autodoc.model.models.tasks.SubTask;
import com.autodoc.model.models.tasks.TemplateSubTask;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
        System.out.println(subTask);
        subTaskDao.create(subTask);
        System.out.println(subTaskDao.getAll());
    }
}