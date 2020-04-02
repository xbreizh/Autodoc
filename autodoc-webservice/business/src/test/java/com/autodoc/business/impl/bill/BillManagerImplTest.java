/*
package com.autodoc.business.impl.bill;

import com.autodoc.business.contract.bill.BillManager;
import com.autodoc.business.contract.pieces.PieceManager;
import com.autodoc.business.contract.tasks.TaskManager;
import com.autodoc.dao.contract.bill.BillDao;
import com.autodoc.dao.contract.car.CarDao;
import com.autodoc.dao.contract.person.client.ClientDao;
import com.autodoc.dao.contract.person.employee.EmployeeDao;
import com.autodoc.dao.contract.pieces.PieceDao;
import com.autodoc.dao.contract.tasks.TaskDao;
import com.autodoc.model.dtos.bill.BillDTO;
import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.employee.Employee;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.model.models.tasks.Task;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

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
    PieceManager pieceManager;
    TaskManager taskManager;
    TaskDao taskDao;
    PieceDao pieceDao;
    Bill bill;
    BillDTO dto;

  */
/*  @BeforeEach
    void init() {
        billDao = mock(BillDao.class);
        carDao = mock(CarDao.class);
        employeeDao = mock(EmployeeDao.class);
        clientDao = mock(ClientDao.class);
        taskDao = mock(TaskDao.class);
        taskManager = mock(TaskManager.class);
        pieceManager = mock(PieceManager.class);
        pieceDao = mock(PieceDao.class);
        billManager = new BillManagerImpl(billDao, carDao, clientDao, employeeDao, taskDao, pieceDao, taskManager, pieceManager);
    }*//*


    @Test
    void getAll() {
        assertEquals(3, billManager.getAll().size());
    }


    @Test
    void add() throws Exception {
        int id = 33;
        BillDTO dto = new BillDTO();
        dto.setStatus("PENDING_PIECES");
        dto.setTotal(129.98);
        dto.setRegistration("D12447F");
        dto.setDateReparation("27-03-2020 11:06");
        dto.setEmployeeId(1);
        dto.setDiscount(20.00);
        dto.setClientId(1);
        dto.setVat(19.5);
        List<Integer> tasks = new ArrayList<>();
        tasks.add(1);
        dto.setTasks(tasks);
        when(carDao.getCarByRegistration(anyString())).thenReturn(new Car());
        when(taskDao.getById(anyInt())).thenReturn(new Task());
        when(clientDao.getById(anyInt())).thenReturn(new Client());
        when(employeeDao.getById(anyInt())).thenReturn(new Employee());
        Bill bill = (Bill) billManager.dtoToEntity(dto);

        System.out.println("bill :" + bill);

    }

    @Test
    @Disabled
    void entityToDto() {

    }

   */
/* @Test
    @Disabled
    void dtoToEntity() {
        dto = new BillDTO();
        dto.setDateReparation(new Date());
    }*//*

}*/
