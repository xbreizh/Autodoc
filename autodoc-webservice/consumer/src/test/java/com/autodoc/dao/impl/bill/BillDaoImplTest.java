/*
package com.autodoc.dao.impl.bill;

import com.autodoc.dao.contract.bill.BillDao;
import com.autodoc.dao.contract.car.CarDao;
import com.autodoc.dao.contract.person.employee.EmployeeDao;
import com.autodoc.dao.contract.tasks.TaskDao;
import com.autodoc.dao.filler.Filler;
import com.autodoc.model.models.bill.Bill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)*/
//@Sql(scripts = "classpath:resetDb_scripts/resetDbCar.sql")
/*@Transactional
class BillDaoImplTest {


    String clientName;
    String registration;
    int id;
    Bill bill;
    @Inject
    private BillDao dao;
    @Inject
    private Filler filler;
    @Inject
    private CarDao carDao;
    @Inject
    private EmployeeDao employeeDao;
    @Inject
    private TaskDao taskDao;

    @BeforeEach
    void init() throws Exception {
        filler.fill();
        bill = (Bill) dao.getAll().get(0);
        id = bill.getId();

    }


  */
/*  @Test
    @DisplayName("should add bill")
    void create() {
        assertEquals(3, dao.getAll().size());
        Date date = new Date();
        Status status = Status.PENDING_PIECES;
        Car car = (Car) carDao.getAll().get(0);
        Employee employee = (Employee) employeeDao.getAll().get(0);
        List<Task> tasks = new ArrayList<>();
        Task task = (Task) taskDao.getAll().get(0);
        tasks.add(task);
        double total = 129.98;
        double vat = 20.00;
        double discount = 00;
        Bill bill = new Bill(date, status, car, employee, car.getClient(), tasks, total, vat, discount);
        dao.create(bill);
        assertEquals(4, dao.getAll().size());
    }*//*


    @Test
    void getAll() {
        assertEquals(3, dao.getAll().size());
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {

        assertTrue(dao.deleteById(2));
    }
}*/
