/*
package com.autodoc.dao.filler;

import com.autodoc.dao.contract.bill.BillDao;
import com.autodoc.dao.contract.car.CarDao;
import com.autodoc.dao.contract.car.CarModelDao;
import com.autodoc.dao.contract.car.ManufacturerDao;
import com.autodoc.dao.contract.person.client.ClientDao;
import com.autodoc.dao.contract.person.employee.EmployeeDao;
import com.autodoc.dao.contract.person.provider.AddressDao;
import com.autodoc.dao.contract.person.provider.CountryDao;
import com.autodoc.dao.contract.person.provider.ProviderDao;
import com.autodoc.dao.contract.pieces.PieceDao;
import com.autodoc.dao.contract.pieces.PieceTypeDao;
import com.autodoc.dao.contract.tasks.TaskDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
@Transactional
class RemoverTest {

    @Inject
    Filler filler;
    @Inject
    Remover remover;
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
    @Inject
    private CountryDao countryDao;
    @Inject
    private ProviderDao providerDao;
    @Inject
    private PieceDao pieceDao;
    @Inject
    private PieceTypeDao pieceTypeDao;
    @Inject
    private TaskDao taskDao;

    @Inject
    private AddressDao addressDao;
    @Inject
    private BillDao billDao;

    @BeforeEach()
    void init() throws InterruptedException, ParseException {
       */
/* filler.fillEmployee();
        filler.fillManufacturer();
        Thread.sleep(2);
        filler.fillCarModel();
        filler.fillClient();
        Thread.sleep(2);
        filler.fillCar();
        filler.fillCountry();*//*
 */
/*
        filler.fillSkillCategory();
        Thread.sleep(2);
        filler.fillSkill();*//*
 */
/*
        filler.fillProvider();
        filler.fillAddresses();
        Thread.sleep(2);
        filler.fillPieceTypes();
        Thread.sleep(2);
        filler.fillPieces();
        Thread.sleep(2);
        filler.fillTasks();
        Thread.sleep(2);
        filler.fillBills();
        Thread.sleep(2); // quick pause between fill up if required*//*

    }

    @Test
    void fill() throws Exception {
        assertNotEquals(0, billDao.getAll().size());
        remover.remover();
        assertEquals(0, billDao.getAll().size());

    }

    @Test
    void fillBills() {
        assertEquals(3, billDao.getAll().size());
    }

    @Test
    void fillAddresses() {
        assertEquals(2, addressDao.getAll().size());
    }

    @Test
    void fillTasks() {
        assertEquals(3, taskDao.getAll().size());
    }


    @Test
    void fillPieceTypes() {
        assertEquals(3, pieceTypeDao.getAll().size());
    }

    @Test
    void fillPieces() {
        assertEquals(4, pieceDao.getAll().size());
    }


    @Test
    void fillProvider() {
        assertEquals(2, providerDao.getAll().size());
    }

  */
/*  @Test
    void fillSkill() {
        assertEquals(3, skillDao.getAll().size());
    }

    @Test
    void fillSkillCategory() {
        assertEquals(5, skillCategoryDao.getAll().size());
    }*//*


    @Test
    void fillCountry() {
        assertEquals(6, countryDao.getAll().size());
    }

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
        assertEquals(2, clientDao.getAll().size());
    }

    @Test
    void fillCar() {
        assertEquals(2, carDao.getAll().size());
    }

    @Test
    void fillEmployee() {
        assertEquals(2, employeeDao.getAll().size());
    }
}*/
