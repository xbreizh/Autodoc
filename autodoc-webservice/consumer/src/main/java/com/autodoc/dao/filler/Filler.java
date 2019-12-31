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
import com.autodoc.model.enums.FuelType;
import com.autodoc.model.enums.GearBox;
import com.autodoc.model.enums.Role;
import com.autodoc.model.enums.Status;
import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.car.Manufacturer;
import com.autodoc.model.models.employee.Employee;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.model.models.person.provider.Address;
import com.autodoc.model.models.person.provider.Country;
import com.autodoc.model.models.person.provider.Provider;
import com.autodoc.model.models.pieces.Piece;
import com.autodoc.model.models.pieces.PieceType;
import com.autodoc.model.models.tasks.Task;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Named
public class Filler {

    static final Logger LOGGER = Logger.getLogger(Filler.class);

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

    public Filler() {

    }


    public void fill() throws Exception {
        if (manufacturerDao.getAll().isEmpty()) {

            LOGGER.debug("getting here");
            fillEmployee();
            fillManufacturer();
            fillCarModel();
            fillClient();
            fillCar();
            fillCountry();
            fillProvider();
            fillPieceTypes();
            fillPieces();
            fillTasks();
            fillAddresses();
            fillBills();
        }
    }

    void fillBills() {
        LOGGER.debug("filling bills");
        Car car = (Car) carDao.getAll().get(1);
        Car car2 = (Car) carDao.getAll().get(1);
        Employee employee = (Employee) employeeDao.getAll().get(0);
        Client client = (Client) clientDao.getAll().get(0);
        List<Task> tasks = taskDao.getAll();
        Bill bill1 = new Bill(new Date(), Status.PENDING, car, employee, client, tasks, 125.44, 19, 20);
        Bill bill2 = new Bill(new Date(), Status.PENDING, car2, employee, client, tasks, 84.44, 19, 0);
        Bill bill3 = new Bill(new Date(), Status.CANCELLED, car, employee, client, tasks, 1451.44, 19, 0);
        billDao.create(bill1);
        billDao.create(bill2);
        billDao.create(bill3);

    }


    void fillAddresses() {
        LOGGER.debug("filling addresses");
        Country country1 = (Country) countryDao.getAll().get(0);
        Country country2 = (Country) countryDao.getAll().get(1);
        LOGGER.info("country1: " + country1);
        Address address1 = new Address(country1, "21, Corry street", "Biarritz");
        Address address2 = new Address(country2, "23, Madison Boulevard", "Portmarnock");
        addressDao.create(address1);
        addressDao.create(address2);
    }


    void fillTasks() {
        LOGGER.debug("filling Tasks");
        Task task1 = new Task("BATTERY CHANGE", "BATTERY CHANGE", 123, 60.00, true);
        Task task2 = new Task("BULB CHANGE", "INSPECTION, CHANGE IF REQUIRED", 123, 30, false);
        Task task3 = new Task("OIL CHANGE", "CHECK AND CHANGE OIL, CHECK FILTER", 123, 120, false);
        taskDao.create(task1);
        taskDao.create(task2);
        taskDao.create(task3);

    }


    void fillPieceTypes() {
        LOGGER.debug("filling pieceCategories");
        PieceType pieceType1 = new PieceType("BRAKE");
        PieceType pieceType2 = new PieceType("LAMP");
        PieceType pieceType3 = new PieceType("SCREW");
        pieceTypeDao.create(pieceType1);
        pieceTypeDao.create(pieceType2);
        pieceTypeDao.create(pieceType3);
    }

    void fillPieces() {
        LOGGER.debug("filling pieces");
        Provider provider = (Provider) providerDao.getById(1);
        PieceType pieceType1 = (PieceType) pieceTypeDao.getById(1);
        PieceType pieceType2 = (PieceType) pieceTypeDao.getById(2);
        CarModel carModel = (CarModel) carModelDao.getAll().get(0);
        Piece piece1 = new Piece(provider, pieceType1, "BRAKE PAD DE4", "DEVO", 10, 14, 2);
        Piece piece2 = new Piece(provider, pieceType1, "GAZOL FILTER 1/454", "MAKO", 20, 84, 0);
        Piece piece3 = new Piece(provider, pieceType2, "WINTER TYRE 15/4587", "PLOUGHLY", 300, 314, 33);
        Piece piece4 = new Piece(provider, pieceType2, "CLUTCH PEDAL CARBON", "MANIET", 40, 88, 3);
        piece1.setCarModel(carModel);
        piece2.setCarModel(carModel);
        piece3.setCarModel(carModel);
        piece4.setCarModel(carModel);
        pieceDao.create(piece1);
        pieceDao.create(piece2);
        pieceDao.create(piece3);
        pieceDao.create(piece4);
    }


    void fillProvider() {
        LOGGER.debug("filling providers");
        Provider provider = new Provider("Paul", "Morigo", "121215", "info@mazda.ie", "MAZDA");
        providerDao.create(provider);
        Provider provider1 = new Provider("JACQUES", "PLACO", "124542", "info@RENAUDO.ie", "RENAUDO");
        providerDao.create(provider1);
    }


    void fillCountry() {
        LOGGER.debug("filling countries");
        String[] list = {"SPAIN", "IRELAND", "MEXICO", "JAPAN", "NEW-ZEALAND", "BELGIUM"};
        for (int i = 0; i < list.length; i++) {
            countryDao.create(new Country(list[i]));
        }
    }

    void fillManufacturer() {
        LOGGER.debug("filling manufacturer");
        String[] list = {"AUDI", "BMW", "RENAULT", "OPEL", "NISSAN", "TOYOTA"};
        for (int i = 0; i < list.length; i++) {
            manufacturerDao.create(new Manufacturer(list[i]));
        }
    }


    void fillCarModel() {
        LOGGER.debug("filling car model");
        Manufacturer man = (Manufacturer) manufacturerDao.getByName("NISSAN");
        carModelDao.create(new CarModel(man, "QASHQAI", "VISIA DCI", GearBox.AUTOMATIC, "1528", FuelType.DIESEL));
        man = (Manufacturer) manufacturerDao.getByName("RENAULT");
        carModelDao.create(new CarModel(man, "CLIO", "BEBOP", GearBox.MANUAL, "1528", FuelType.PETROL));
        man = (Manufacturer) manufacturerDao.getByName("TOYOTA");
        carModelDao.create(new CarModel(man, "AURIS", "T SPIRIT D4D", GearBox.MANUAL, "1998", FuelType.HYBRID));
    }

    void fillClient() {
        LOGGER.debug("filling client");
        Client client = new Client("LOKII", "MOLO", "03938937837");
        Client client1 = new Client("ROGER", "MOORE", "0584759852");
        clientDao.create(client);
        clientDao.create(client1);
    }

    void fillCar() {
        LOGGER.debug("filling car");
        CarModel carModel = (CarModel) carModelDao.getByName("AURIS");
        CarModel carModel1 = (CarModel) carModelDao.getByName("CLIO");
        Client client = (Client) clientDao.getAll().get(0);
        Car car = new Car("05D154875", carModel, client);
        Car car1 = new Car("D12447F", carModel1, client);
        carDao.create(car);
        carDao.create(car1);
    }

    void fillEmployee() throws ParseException {
        LOGGER.debug("filling employee");
        List<Role> roleList = new ArrayList<>();
        String login = "LMOLO";
        String pattern = "MM-dd-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse("12-01-2018");
        Employee employee = new Employee("LOKII", "MOLO", "03938937837", roleList, date, login, "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6");
        List<Role> roles = new ArrayList<>();
        roles.add(Role.MECANIC);
        employee.setRoles(roles);
        String login2 = "MALIK";
        Employee employee2 = new Employee("MALIK", "GAUMONT", "0862547895", roleList, date, login2, "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6");
        List<Role> roles2 = new ArrayList<>();
        roles2.add(Role.MECANIC);
        roles2.add(Role.MANAGER);
        employee.setRoles(roles);
        employee2.setRoles(roles2);
        employeeDao.create(employee);
        employeeDao.create(employee2);
    }


}

