package com.autodoc.dao.filler;

import com.autodoc.dao.contract.bill.BillDao;
import com.autodoc.dao.contract.car.CarDao;
import com.autodoc.dao.contract.car.CarModelDao;
import com.autodoc.dao.contract.car.ManufacturerDao;
import com.autodoc.dao.contract.person.client.ClientDao;
import com.autodoc.dao.contract.person.employee.EmployeeDao;
import com.autodoc.dao.contract.person.provider.ProviderDao;
import com.autodoc.dao.contract.pieces.PieceDao;
import com.autodoc.dao.contract.pieces.PieceTypeDao;
import com.autodoc.dao.contract.tasks.TaskDao;
import org.apache.log4j.BasicConfigurator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
@Transactional
class FillerTest {

    @Inject
    Filler filler;
    @Inject
    private ManufacturerDao manufacturerDao;
    @Inject
    private CarModelDao carModelDao;
    @Inject
    private EmployeeDao employeeDao;
    @Inject
    private ClientDao clientDao;
    @Inject
    private CarDao carDao;
    /*   @Inject
       private CountryDao countryDao;*/
    @Inject
    private ProviderDao providerDao;
    @Inject
    private PieceDao pieceDao;
    @Inject
    private PieceTypeDao pieceTypeDao;
    @Inject
    private TaskDao taskDao;
    @Inject
    private BillDao billDao;

    @BeforeEach()
    void init() throws ParseException {
      //  BasicConfigurator.configure();
        filler.fill();
    }


    @Test
    void fillBills() {
        int billSize = billDao.getAll().size();
        assertEquals(billSize, billDao.getAll().size());
    }


    @Test
    void fillTasks() {
        assertEquals(4, taskDao.getAll().size());
    }


    @Test
    void fillPieceTypes() {
        assertEquals(5, pieceTypeDao.getAll().size());
    }

    @Test
    void fillPieces() {
        assertEquals(5, pieceDao.getAll().size());
    }


    @Test
    void fillProvider() {
        assertEquals(2, providerDao.getAll().size());
    }


   /* @Test
    void fillCountry() {
        assertEquals(6, countryDao.getAll().size());
    }*/

    @Test
    void fillManufacturer() {
        assertEquals(6, manufacturerDao.getAll().size());
    }

    @Test
    void fillCarModel() {
        assertEquals(3, carModelDao.getAll().size());
    }

    @Test
    void fillClient() {
        assertEquals(3, clientDao.getAll().size());
    }

    @Test
    void fillCar() {
        assertEquals(2, carDao.getAll().size());
    }

    @Test
    void fillEmployee() {
        assertEquals(2, employeeDao.getAll().size());
    }
}
