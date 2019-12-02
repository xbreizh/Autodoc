package com.autodoc.dao.filler;

import com.autodoc.dao.contract.bill.BillDao;
import com.autodoc.dao.contract.car.CarDao;
import com.autodoc.dao.contract.car.CarModelDao;
import com.autodoc.dao.contract.car.ManufacturerDao;
import com.autodoc.dao.contract.person.client.ClientDao;
import com.autodoc.dao.contract.person.employee.EmployeeDao;
import com.autodoc.dao.contract.person.employee.SkillCategoryDao;
import com.autodoc.dao.contract.person.employee.SkillDao;
import com.autodoc.dao.contract.person.provider.AddressDao;
import com.autodoc.dao.contract.person.provider.CountryDao;
import com.autodoc.dao.contract.person.provider.ProviderDao;
import com.autodoc.dao.contract.pieces.PieceDao;
import com.autodoc.dao.contract.pieces.PieceTypeDao;
import com.autodoc.dao.contract.tasks.SubTaskDao;
import com.autodoc.dao.contract.tasks.TaskDao;
import com.autodoc.dao.contract.tasks.TemplateSubTaskDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
//@Sql(scripts = "classpath:resetDb_scripts/resetDbCar.sql")
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
    @Inject
    private CountryDao countryDao;
    @Inject
    private SkillCategoryDao skillCategoryDao;
    @Inject
    private SkillDao skillDao;
    @Inject
    private ProviderDao providerDao;
    @Inject
    private TemplateSubTaskDao templateSubTaskDao;
    @Inject
    private PieceDao pieceDao;
    @Inject
    private PieceTypeDao pieceTypeDao;
    @Inject
    private TaskDao taskDao;
    @Inject
    private SubTaskDao subTaskDao;
    @Inject
    private AddressDao addressDao;
    @Inject
    private BillDao billDao;

    @BeforeEach()
    void init() throws InterruptedException {
        filler.fillEmployee();
        filler.fillManufacturer();
        filler.fillCarModel();
        filler.fillClient();
        filler.fillCar();
        filler.fillCountry();
        filler.fillSkillCategory();
        filler.fillSkill();
        filler.fillProvider();
        filler.fillPieceTypes();
        filler.fillPieces();
        filler.fillTemplateSubTask();
        filler.fillSubTasks();
        filler.fillTasks();
        filler.fillAddresses();
        filler.fillBills();
        // Thread.sleep(2); // quick pause between fill up if required
    }

    @Test
    void fill() throws Exception {

        //()-> assertEquals(2, addressDao.getAll().size()),
        //()-> assertEquals(2, billDao.getAll().size())

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
    void fillSubTasks() {
        assertEquals(1, subTaskDao.getAll().size());
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
    void fillTemplateSubTask() {
        assertEquals(1, templateSubTaskDao.getAll().size());
    }

    @Test
    void fillProvider() {
        assertEquals(2, providerDao.getAll().size());
    }

    @Test
    void fillSkill() {
        assertEquals(3, skillDao.getAll().size());
    }

    @Test
    void fillSkillCategory() {
        assertEquals(5, skillCategoryDao.getAll().size());
    }

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
}