package com.autodoc.business.filler;

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
import com.autodoc.model.enums.FuelType;
import com.autodoc.model.enums.GearBox;
import com.autodoc.model.enums.Role;
import com.autodoc.model.enums.Status;
import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.car.Manufacturer;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.model.models.person.employee.Employee;
import com.autodoc.model.models.person.employee.Skill;
import com.autodoc.model.models.person.employee.SkillCategory;
import com.autodoc.model.models.person.provider.Address;
import com.autodoc.model.models.person.provider.Country;
import com.autodoc.model.models.person.provider.Provider;
import com.autodoc.model.models.pieces.Piece;
import com.autodoc.model.models.pieces.PieceType;
import com.autodoc.model.models.tasks.SubTask;
import com.autodoc.model.models.tasks.Task;
import com.autodoc.model.models.tasks.TemplateSubTask;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Component
public class Filler {

    private static final Logger LOGGER = Logger.getLogger(Filler.class);

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

    public Filler() {

    }

    public void fill() {
        LOGGER.debug("getting here");
        fillEmployee();
        fillManufacturer();
        fillCarModel();
        fillClient();
        fillCar();
        fillCountry();
        fillSkillCategory();
        fillSkill();
        fillProvider();
        fillPieceTypes();
        fillPieces();
        fillTemplateSubTask();
        fillSubTasks();
        fillTasks();
        fillAddresses();
        fillBills();
    }

    private void fillBills() {
        LOGGER.debug("filling bills");
        Car car = (Car) carDao.getById(1);
        Car car2 = (Car) carDao.getById(2);
        Employee employee = (Employee) employeeDao.getById(1);
        List<Task> tasks = taskDao.getAll();
        Bill bill1 = new Bill(new Date(), Status.PENDING, car,  employee, tasks, 125.44, 19, 20);
        Bill bill2 = new Bill(new Date(), Status.PENDING, car2, employee, tasks, 84.44, 19, 0);
        Bill bill3 = new Bill(new Date(), Status.PENDING, car,  employee, tasks, 1451.44, 19, 0);
        billDao.create(bill1);
        /*billDao.create(bill2);
        billDao.create(bill3);*/

    }


    private void fillAddresses() {
        LOGGER.debug("filling addresses");
        Country country1 = (Country) countryDao.getById(1);
        Country country2 = (Country) countryDao.getById(3);
        Address address1 = new Address(country1, "21, Corry street", "Biarritz");
        Address address2 = new Address(country2, "23, Madison Boulevard", "Portmarnock");
        addressDao.create(address1);
        addressDao.create(address2);
    }


    private void fillTasks() {
        LOGGER.debug("filling Tasks");
        List<SubTask> subTasks = subTaskDao.getAll();
        System.out.println("sub: " + subTasks.get(0));
        Task task1 = new Task("BATTERY CHANGE", subTasks.subList(0,1), 123);
        Task task2 = new Task("BULB CHANGE", subTasks.subList(0,1), 123);
        Task task3 = new Task("OIL CHANGE", subTasks.subList(0,1), 123);
        taskDao.create(task1);
        taskDao.create(task2);
        taskDao.create(task3);

    }

    private void fillSubTasks() {
        LOGGER.debug("filling subTasks");
        System.out.println("filling subtask");
        List<Employee> employees = employeeDao.getAll();
        List<Piece> pieces = pieceDao.getAll();
        SubTask subTask1 = new SubTask();
        subTask1.setName("name");
        subTask1.setPieces(pieces);
        subTask1.setEmployees(employees);
        subTask1.setEstimatedTime(11);
        TemplateSubTask t = (TemplateSubTask) templateSubTaskDao.getById(1);
        subTask1.setTemplateSubTask(t);
        System.out.println(subTask1);
        subTask1.setId(12);
       /* SubTask subTask2 = new SubTask(pieces, employees, "SUBTASK2", 50);
        SubTask subTask3 = new SubTask(pieces, employees, "SUBTASK3", 80);*/
        //subTask3.setTemplateSubTask((TemplateSubTask) templateSubTaskDao.getById(1));
        //System.out.println("subtask 1: " + subTask1);
       // System.out.println(subTask1);
        subTaskDao.create(subTask1);
       /* subTaskDao.create(subTask2);
        subTaskDao.create(subTask3);*/
    }


    private void fillPieceTypes() {
        LOGGER.debug("filling pieceCategories");
        PieceType pieceType1 = new PieceType("BRAKE");
        PieceType pieceType2 = new PieceType("LAMP");
        PieceType pieceType3 = new PieceType("SCREW");
        pieceTypeDao.create(pieceType1);
        pieceTypeDao.create(pieceType2);
        pieceTypeDao.create(pieceType3);
    }

    private void fillPieces() {
        LOGGER.debug("filling pieces");
        Provider provider = (Provider) providerDao.getById(1);
        PieceType pieceType1 = (PieceType) pieceTypeDao.getById(1);
        PieceType pieceType2 = (PieceType) pieceTypeDao.getById(2);
        Piece piece1 = new Piece(provider, pieceType1, "BRAKE PAD DE4", "DEVO", 10, 14);
        Piece piece2 = new Piece(provider, pieceType1, "GAZOL FILTER 1/454", "MAKO", 20, 84);
        Piece piece3 = new Piece(provider, pieceType2, "WINTER TYRE 15/4587", "PLOUGHLY", 300, 314);
        Piece piece4 = new Piece(provider, pieceType2, "CLUTCH PEDAL CARBON", "MANIET", 40, 88);
        pieceDao.create(piece1);
        pieceDao.create(piece2);
        pieceDao.create(piece3);
        pieceDao.create(piece4);
    }

    private void fillTemplateSubTask() {
        LOGGER.debug("filling templateSubTasks");
        List<Piece> pieces = pieceDao.getAll();
        TemplateSubTask templateSubTask1 = new TemplateSubTask(pieces, "Template", 123);
        templateSubTaskDao.create(templateSubTask1);
    }

    private void fillProvider() {
        LOGGER.debug("filling providers");
        Provider provider = new Provider("Paul", "Morigo", "121215", "info@mazda.ie", "MAZDA");
        providerDao.create(provider);
        Provider provider1 = new Provider("JACQUES", "PLACO", "124542", "info@RENAUDO.ie", "RENAUDO");
        providerDao.create(provider1);
    }

    private void fillSkill() {
        LOGGER.debug("filling skills");
        Skill skill = new Skill();
        Skill skill1 = new Skill();
        Skill skill2 = new Skill();
        SkillCategory skillCat1 = (SkillCategory) skillCategoryDao.getById(1);
        SkillCategory skillCat2 = (SkillCategory) skillCategoryDao.getById(1);
        skill.setSkillCategory(skillCat1);
        skill.setName("changement de courroie");
        skillDao.create(skill);
        skill1.setSkillCategory(skillCat2);
        skill1.setName("changement de tambours");
        skillDao.create(skill1);
        skill2.setSkillCategory(skillCat1);
        skill2.setName("changement de plaquettes");
        skillDao.create(skill2);
    }


    private void fillSkillCategory() {
        LOGGER.debug("filling skill categories");
        String[] list = {"VIDANGE", "FREIN", "PNEUS", "MAINTENANCE", "CLIMATISATION"};
        for (int i = 0; i < list.length; i++) {
            skillCategoryDao.create(new SkillCategory(list[i]));
        }

    }


    private void fillCountry() {
        LOGGER.debug("filling countries");
        String[] list = {"SPAIN", "IRELAND", "MEXICO", "JAPAN", "NEW-ZEALAND", "BELGIUM"};
        for (int i = 0; i < list.length; i++) {
            countryDao.create(new Country(list[i]));
        }
    }

    private void fillManufacturer() {
        LOGGER.debug("filling manufacturer");
        String[] list = {"AUDI", "BMW", "RENAULT", "OPEL", "NISSAN", "TOYOTA"};
        for (int i = 0; i < list.length; i++) {
            manufacturerDao.create(new Manufacturer(list[i]));
        }
    }


    private void fillCarModel() {
        LOGGER.debug("filling car model");
        Manufacturer man = manufacturerDao.getByName("NISSAN");
        carModelDao.create(new CarModel(man, "QASHQAI", "VISIA DCI", GearBox.AUTOMATIC, "1528", FuelType.DIESEL));
        man = manufacturerDao.getByName("RENAULT");
        carModelDao.create(new CarModel(man, "CLIO", "BEBOP", GearBox.MANUAL, "1528", FuelType.PETROL));
        man = manufacturerDao.getByName("TOYOTA");
        carModelDao.create(new CarModel(man, "AURIS", "T SPIRIT D4D", GearBox.MANUAL, "1998", FuelType.HYBRID));
    }

    private void fillClient() {
        LOGGER.debug("filling client");
        Client client = new Client("LOKII", "MOLO", "03938937837");
        Client client1 = new Client("ROGER", "MOORE", "0584759852");
        clientDao.create(client);
        clientDao.create(client1);
    }

    private void fillCar() {
        LOGGER.debug("filling car");
        CarModel carModel = carModelDao.findByName("AURIS");
        CarModel carModel1 = carModelDao.findByName("CLIO");
        Client client = (Client) clientDao.getAll().get(0);
        Car car = new Car("05D154875", carModel, client);
        Car car1 = new Car("D12447F", carModel1, client);
        carDao.create(car);
        carDao.create(car1);
    }

    private void fillEmployee() {
        LOGGER.debug("filling employee");
        List<Role> roleList = new ArrayList<>();
        String login = "LMOLO";
        Employee employee = new Employee("LOKII", "MOLO", "03938937837", roleList, new Date(), login, "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6");
        List<Role> roles = new ArrayList<>();
        roles.add(Role.MECANIC);
        employee.setRoles(roles);
        employeeDao.create(employee);
    }


}
