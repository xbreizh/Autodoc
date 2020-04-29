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
import com.autodoc.model.enums.*;
import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.car.Manufacturer;
import com.autodoc.model.models.employee.Employee;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.model.models.person.provider.Provider;
import com.autodoc.model.models.pieces.Piece;
import com.autodoc.model.models.pieces.PieceType;
import com.autodoc.model.models.tasks.Task;
import lombok.Builder;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Named
@Builder
public class Filler {

    static final Logger LOGGER = Logger.getLogger(Filler.class);
    private ManufacturerDao manufacturerDao;
    private CarModelDao carModelDao;
    private EmployeeDao employeeDao;
    private ClientDao clientDao;
    private CarDao carDao;
    //  private CountryDao countryDao;
    private ProviderDao providerDao;
    private PieceDao pieceDao;
    private PieceTypeDao pieceTypeDao;
    private TaskDao taskDao;
    private BillDao billDao;


    public void fill() throws ParseException {
        if (manufacturerDao.getAll().isEmpty()) {
            LOGGER.debug("getting here");
            fillEmployee();
            fillManufacturer();
            fillCarModel();
            fillClient();
            fillCar();
            // fillCountry();
            fillProvider();
            fillPieceTypes();
            fillPieces();
            fillTasks();
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
        List<Piece> pieces = pieceDao.getAll();
        Bill bill1 = Bill.builder().dateReparation(new Date()).status(Status.PENDING_PAYMENT).car(car).employee(employee).client(client).tasks(tasks).total(125.44).vat(19).discount(20).pieces(pieces).comments("plouf").build();
        Bill bill2 = Bill.builder().dateReparation(new Date()).status(Status.PENDING_PIECES).car(car2).employee(employee).client(client).tasks(tasks).total(84.44).vat(19).discount(0).pieces(pieces).build();
        Bill bill3 = Bill.builder().dateReparation(new Date()).status(Status.CANCELLED).car(car).employee(employee).client(client).tasks(tasks).total(1451.44).vat(19).discount(20).pieces(pieces).build();
        Bill bill4 = Bill.builder().dateReparation(new Date()).status(Status.COMPLETED).paymentType(PaymentType.CASH).car(car).employee(employee).client(client).tasks(tasks).total(1451.44).vat(19).discount(20).pieces(pieces).build();
        billDao.create(bill1);
        billDao.create(bill2);
        billDao.create(bill3);
        billDao.create(bill4);

    }


    void fillTasks() {
        LOGGER.debug("filling Tasks");
        Task task1 = Task.builder().name("battery change").description("battery change").estimatedTime(60).build();
        Task task2 = Task.builder().name("BULB CHANGE").description("INSPECTION, CHANGE IF REQUIRED").estimatedTime(30).build();
        Task task3 = Task.builder().name("OIL CHANGE").description("CHECK AND CHANGE OIL, CHECK FILTER").estimatedTime(120).build();
        taskDao.create(task1);
        taskDao.create(task2);
        taskDao.create(task3);

    }


    void fillPieceTypes() {
        LOGGER.debug("filling pieceCategories");
        PieceType pieceType1 = PieceType.builder().name("BRAKE").build();
        PieceType pieceType2 = PieceType.builder().name("LAMP").build();
        PieceType pieceType3 = PieceType.builder().name("SCREW").build();
        pieceTypeDao.create(pieceType1);
        pieceTypeDao.create(pieceType2);
        pieceTypeDao.create(pieceType3);
    }

    void fillPieces() {
        LOGGER.debug("filling pieces");
        PieceType pieceType1 = (PieceType) pieceTypeDao.getById(1);
        PieceType pieceType2 = (PieceType) pieceTypeDao.getById(2);
        Piece piece1 = Piece.builder().pieceType(pieceType1).name("BRAKE PAD DE4").brand("DEVO").buyingPrice(10).sellPrice(14).quantity(2).build();
        Piece piece2 = Piece.builder().pieceType(pieceType2).name("GAZOL FILTER 1/454").brand("MAKO").buyingPrice(20).sellPrice(84).quantity(22).build();
        Piece piece3 = Piece.builder().pieceType(pieceType1).name("WINTER TYRE 15/4587").brand("PLOUGHLY").buyingPrice(300).sellPrice(314).quantity(33).build();
        Piece piece4 = Piece.builder().pieceType(pieceType2).name("CLUTCH PEDAL CARBON").brand("MANIET").buyingPrice(40).sellPrice(88).quantity(2).build();
        pieceDao.create(piece1);
        pieceDao.create(piece2);
        pieceDao.create(piece3);
        pieceDao.create(piece4);
    }


    void fillProvider() {
        LOGGER.debug("filling providers");
        Provider provider = Provider.builder().firstName("Paul").lastName("Morigo").phoneNumber("121215").email1("info@mazda.ie").company("MAZDA").build();
        Provider provider1 = Provider.builder().firstName("JACQUES").lastName("PLACO").phoneNumber("32434343").email1("info@RENAUDO.ie").company("RENAUDO").build();
        providerDao.create(provider);
        providerDao.create(provider1);
    }


 /*   void fillCountry() {
        LOGGER.debug("filling countries");
        String[] list = {"SPAIN", "IRELAND", "MEXICO", "JAPAN", "NEW-ZEALAND", "BELGIUM"};
        for (String country : list) {
            countryDao.create(Country.builder().name(country).build());
        }
    }*/

    void fillManufacturer() {
        LOGGER.debug("filling manufacturer");
        String[] list = {"AUDI", "BMW", "RENAULT", "OPEL", "NISSAN", "TOYOTA"};
        for (String man : list) {
            manufacturerDao.create(Manufacturer.builder().name(man).build());
        }
    }


    void fillCarModel() {
        LOGGER.debug("filling car model");
        Manufacturer man = (Manufacturer) manufacturerDao.getByName("NISSAN");
        Manufacturer man1 = (Manufacturer) manufacturerDao.getByName("TOYOTA");
        Manufacturer man2 = (Manufacturer) manufacturerDao.getByName("RENAULT");
        carModelDao.create(CarModel.builder().manufacturer(man).name("QASHQAI").description("VISIA DCI").gearbox(GearBox.AUTOMATIC).engine("1528").fuelType(FuelType.DIESEL).build());
        carModelDao.create(CarModel.builder().manufacturer(man2).name("CLIO").description("BEBOP").gearbox(GearBox.MANUAL).engine("1528").fuelType(FuelType.PETROL).build());
        carModelDao.create(CarModel.builder().manufacturer(man1).name("AURIS").description("T SPIRIT D4D").gearbox(GearBox.MANUAL).engine("84545").fuelType(FuelType.HYBRID).build());

    }

    void fillClient() {
        LOGGER.debug("filling client");
        Client client = Client.builder().firstName("ANGELA").lastName("BAUER").phoneNumber("03938937837").build();
        Client client1 = Client.builder().firstName("ROGER").lastName("MOORE").phoneNumber("0584759852").build();
        clientDao.create(client);
        clientDao.create(client1);
    }

    void fillCar() {
        LOGGER.debug("filling car");
        CarModel carModel = (CarModel) carModelDao.getByName("AURIS");
        CarModel carModel1 = (CarModel) carModelDao.getByName("CLIO");
        Client client = (Client) clientDao.getAll().get(0);
        Car car = Car.builder().registration("05d121487").carModel(carModel).client(client).mileage(457845).color("red").build();
        Car car1 = Car.builder().registration("wD213232f").carModel(carModel1).client(client).mileage(75845).color("grey").build();
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
        Employee employee = Employee.builder().firstName("paul").lastName("MOLO").phoneNumber("03938937837").roles(roleList).startDate(date).login(login).password("$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6").build();
        List<Role> roles = new ArrayList<>();
        roles.add(Role.MECANIC);
        employee.setRoles(roles);
        String login2 = "MALIK";
        Employee employee2 = Employee.builder().firstName("MALIK").lastName("GAUMONT").phoneNumber("0862547895").roles(roleList).startDate(date).login(login2).password("$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6").build();
        List<Role> roles2 = new ArrayList<>();
        roles2.add(Role.MECANIC);
        roles2.add(Role.MANAGER);
        employee.setRoles(roles);
        employee2.setRoles(roles2);
        employeeDao.create(employee);
        employeeDao.create(employee2);
    }


}

