package com.autodoc.business.impl.bill;

import com.autodoc.business.contract.bill.BillManager;
import com.autodoc.dao.contract.bill.BillDao;
import com.autodoc.dao.contract.car.CarDao;
import com.autodoc.dao.contract.person.client.ClientDao;
import com.autodoc.dao.contract.person.employee.EmployeeDao;
import com.autodoc.dao.contract.tasks.TaskDao;
import com.autodoc.model.dtos.bill.BillDTO;
import com.autodoc.model.models.bill.Bill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
@Transactional
class BillManagerImplTest {

    @Inject
    BillManager billManager;
    BillDao billDao;
    CarDao carDao;
    EmployeeDao employeeDao;
    ClientDao clientDao;
    TaskDao taskDao;
    Bill bill;
    BillDTO dto;

    @BeforeEach
    void init() {
       /* billDao = mock(BillDao.class);
        carDao = mock(CarDao.class);
        clientDao = mock(ClientDao.class);
        employeeDao = mock(EmployeeDao.class);
        taskDao = mock(TaskDao.class);
        billManager = new BillManagerImpl(billDao, carDao, clientDao, employeeDao, taskDao);*/
       /* Date date = new Date();
        Status status = Status.PENDING_PIECES;
        Car car = (Car) carDao.getAll().get(0);
        Employee employee = (Employee) employeeDao.getAll().get(0);
        List<Task> tasks = new ArrayList<>();
        Task task = (Task) taskDao.getAll().get(0);
        tasks.add(task);
        double total = 129.98;
        double vat = 20.00;
        double discount = 00;
        bill = new Bill(date, status, car, employee, car.getClient(), tasks, total, vat, discount);*/
    }

    @Test
    void getAll() {
        assertEquals(3, billManager.getAll().size());
    }


    @Test
    void add() {
        BillDTO dto = new BillDTO();
        dto.setStatus("PENDING_PIECES");
        dto.setTotal(129.98);
        dto.setRegistration("D12447F");
        dto.setEmployeeId(1);
        dto.setDiscount(20.00);
        dto.setClientId(1);
        dto.setVat(19.5);
        List<Integer> tasks = new ArrayList<>();
        tasks.add(1);
        dto.setTasks(tasks);
        assertDoesNotThrow(() -> Integer.parseInt(billManager.save(dto)));

    }

    @Test
    @Disabled
    void entityToDto() {

    }

    @Test
    @Disabled
    void dtoToEntity() {
        dto = new BillDTO();
        dto.setDate(new Date());
    }
}