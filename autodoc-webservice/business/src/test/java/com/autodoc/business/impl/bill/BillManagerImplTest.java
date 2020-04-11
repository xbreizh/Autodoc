package com.autodoc.business.impl.bill;

import com.autodoc.business.contract.bill.BillManager;
import com.autodoc.business.contract.pieces.PieceManager;
import com.autodoc.business.contract.tasks.TaskManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.dao.contract.bill.BillDao;
import com.autodoc.dao.contract.car.CarDao;
import com.autodoc.dao.contract.person.client.ClientDao;
import com.autodoc.dao.contract.person.employee.EmployeeDao;
import com.autodoc.dao.contract.pieces.PieceDao;
import com.autodoc.dao.contract.tasks.TaskDao;
import com.autodoc.model.dtos.bill.BillDTO;
import com.autodoc.model.enums.Status;
import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.employee.Employee;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.model.models.person.provider.Provider;
import com.autodoc.model.models.pieces.Piece;
import com.autodoc.model.models.tasks.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/*@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
@Transactional*/
class BillManagerImplTest {

    BillManager manager;
    BillDao dao;
    CarDao carDao;
    EmployeeDao employeeDao;
    ClientDao clientDao;
    PieceManager pieceManager;
    TaskManager taskManager;
    TaskDao taskDao;
    PieceDao pieceDao;
    Bill obj;
    BillDTO dto;
    int id = 3;
    Status status = Status.PENDING_PAYMENT;
    String comments = "commentaire";
    Date dateReparation = new Date();
    double total = 2323232;
    double vat = 22;
    double discount = 10;
    Client client;
    Employee employee;
    Car car;
    List<Piece> pieces = new ArrayList<>();
    List<Task> tasks = new ArrayList<>();
    Provider provider;
    CarModel carModel;
    String registration = "absce22";

    @BeforeEach
    void init() {
        dao = mock(BillDao.class);
        carDao = mock(CarDao.class);
        employeeDao = mock(EmployeeDao.class);
        clientDao = mock(ClientDao.class);
        taskDao = mock(TaskDao.class);
        taskManager = mock(TaskManager.class);
        pieceManager = mock(PieceManager.class);
        pieceDao = mock(PieceDao.class);
        manager = BillManagerImpl.builder().dao(dao).carDao(carDao).clientDao(clientDao)
                .employeeDao(employeeDao).taskDao(taskDao).pieceDao(pieceDao).taskManager(taskManager)
                .pieceManager(pieceManager).build();
        Task task = Task.builder().id(id).name("nameui").description("descriere").estimatedTime(1.4).build();
        tasks.add(task);
        client = Client.builder().id(id).firstName("firstName").lastName("lastName").phoneNumber("phoneNumber").build();
        employee = Employee.builder().id(id).firstName("firstName").lastName("lastName").phoneNumber("phoneNumber").login("login").password("password").build();
        car = Car.builder().id(id).registration("registration").client(client).carModel(carModel).build();
        provider = Provider.builder().id(id).firstName("firstName").lastName("lastName").phoneNumber("phoneNumber").company("company").email1("email1").website("website").address("3, rue michard").build();
        Piece piece = Piece.builder().name("name").brand("brand").id(id).quantity(2).buyingPrice(23).sellPrice(35).provider(provider).build();
        pieces.add(piece);
        obj = Bill.builder().id(id).car(car).client(client).pieces(pieces).employee(employee).status(status).tasks(tasks).comments(comments).dateReparation(dateReparation)
                .discount(discount).total(total).vat(vat).build();
        dto = BillDTO.builder().id(id).clientId(3).pieces(new ArrayList<>()).employeeId(3).status("status").tasks(new ArrayList<>()).comments(comments)
                .discount(discount).total(total).registration("anbs332").build();
        dto.getPieces().add(34);
        dto.getPieces().add(38);
        dto.getTasks().add(2);
        dto.getTasks().add(265);


    }

    @Test
    @DisplayName("should return obj if existing")
    void getDao() {
        assertEquals(dao, manager.getDao());

    }

    @Test
    @DisplayName("should return className")
    void getEntityClass() {
        assertEquals(Bill.class, manager.getEntityClass());
    }

    @Test
    @DisplayName("should return dtoClassName")
    void getDtoClass() {
        assertEquals(BillDTO.class, manager.getDtoClass());
    }

    @Test
    @DisplayName("should return date format")
    void getDateFormat() {
        assertEquals("dd-MM-yyyy HH:mm", manager.getDateFormat().toPattern());
    }

    @Test
    @DisplayName("should transfer entity into dto")
    void entityToDto() {
        dto = (BillDTO) manager.entityToDto(obj);
        String dateReparationDTO = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(obj.getDateReparation());
        assertAll(
                () -> assertEquals(obj.getId(), dto.getId()),
                () -> assertEquals(obj.getComments(), dto.getComments()),
                () -> assertEquals(dateReparationDTO, dto.getDateReparation()),
                () -> assertEquals(obj.getDiscount(), dto.getDiscount()),
                () -> assertEquals(obj.getCar().getRegistration(), dto.getRegistration()),
                () -> assertEquals(obj.getStatus().toString(), dto.getStatus()),
                () -> assertEquals(obj.getTotal(), dto.getTotal()),
                () -> assertEquals(obj.getClient().getId(), dto.getClientId()),
                () -> assertEquals(obj.getEmployee().getId(), dto.getEmployeeId())
        );
    }

    @Test
    @DisplayName("should transfer entity into dto if pieces and tasks null")
    void entityToDto1() {
        obj.setTasks(null);
        obj.setPieces(null);
        dto = (BillDTO) manager.entityToDto(obj);
        String dateReparationDTO = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(obj.getDateReparation());
        assertAll(
                () -> assertEquals(obj.getId(), dto.getId()),
                () -> assertEquals(obj.getComments(), dto.getComments()),
                () -> assertEquals(dateReparationDTO, dto.getDateReparation()),
                () -> assertEquals(obj.getDiscount(), dto.getDiscount()),
                () -> assertEquals(obj.getCar().getRegistration(), dto.getRegistration()),
                () -> assertEquals(obj.getStatus().toString(), dto.getStatus()),
                () -> assertEquals(obj.getTotal(), dto.getTotal()),
                () -> assertEquals(obj.getClient().getId(), dto.getClientId()),
                () -> assertEquals(obj.getEmployee().getId(), dto.getEmployeeId())
        );
    }

    @Test
    @DisplayName("should update")
    void transferUpdate() {
        when(dao.getById(anyInt())).thenReturn(obj);
        dto.setRegistration(registration);
        car.setRegistration(registration);
        dto.setDateReparation("10-04-2020 18:26");
        dto.setStatus("PENDING_PIECES");
        when(carDao.getCarByRegistration(anyString())).thenReturn(car);
        when(taskDao.getById(anyInt())).thenReturn(new Task());
        Piece piece = new Piece();
        piece.setName("nomo");
        when(pieceDao.getById(anyInt())).thenReturn(piece);
        when(clientDao.getById(anyInt())).thenReturn(client);
        when(employeeDao.getById(anyInt())).thenReturn(employee);
        obj = (Bill) manager.transferUpdate(dto);
        String dateReparationDTO = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(obj.getDateReparation());
        assertAll(
                () -> assertEquals(dto.getId(), obj.getId()),
                () -> assertEquals(dto.getComments().toUpperCase(), obj.getComments()),
                () -> assertEquals(dto.getDiscount(), obj.getDiscount()),
                () -> assertEquals(dto.getRegistration().toUpperCase(), obj.getCar().getRegistration()),
                () -> assertEquals(dto.getStatus(), obj.getStatus().toString()),
                () -> assertEquals(dto.getTotal(), obj.getTotal()),
                () -> assertEquals(dto.getEmployeeId(), obj.getEmployee().getId()),
                () -> assertEquals(dto.getDateReparation(), dateReparationDTO),
                () -> assertEquals(dto.getClientId(), obj.getClient().getId())
        );

    }

    @Test
    @DisplayName("should throw an exception id id==0")
    void transferUpdate1() {
        dto.setId(0);
        assertThrows(InvalidDtoException.class, () -> manager.transferUpdate(dto));

    }

    @Test
    @DisplayName("should update bill status")
    void updateBillStatusIfMissingPiece() {
        Piece p1 = pieces.get(0);
        p1.setName("OOS trucPiece");
        obj.getPieces().add(p1);
        obj.setStatus(Status.PENDING_PAYMENT);
        manager.updateBillStatusIfMissingPiece(obj);
        assertEquals(Status.PENDING_PIECES, obj.getStatus());
    }

    @Test
    @DisplayName("should not update bill status")
    void updateBillStatusIfMissingPiece1() {
        Piece p1 = pieces.get(0);
        p1.setName("trucPiece");
        obj.getPieces().add(p1);
        obj.setStatus(Status.PENDING_PAYMENT);
        manager.updateBillStatusIfMissingPiece(obj);
        assertEquals(Status.PENDING_PAYMENT, obj.getStatus());
    }

    @Test
    @DisplayName("should transferEmployee")
    void transferEmployee() {
        Employee emp = new Employee();
        System.out.println(dto);
        when(employeeDao.getById(anyInt())).thenReturn(emp);

        assertAll(
                () -> assertDoesNotThrow(() -> manager.transferEmployee(dto, obj)),
                () -> assertEquals(emp, obj.getEmployee())
        );

    }

    @Test
    @DisplayName("should throw an exception when employee invalid")
    void transferEmployee1() {
        Employee emp = new Employee();
        System.out.println(dto);
        when(employeeDao.getById(anyInt())).thenReturn(null);
        assertThrows(InvalidDtoException.class, () -> manager.transferEmployee(dto, obj));
    }

    @Test
    @DisplayName("should transferClient")
    void transferClient() {
        Client emp = new Client();
        System.out.println(dto);
        when(clientDao.getById(anyInt())).thenReturn(emp);

        assertAll(
                () -> assertDoesNotThrow(() -> manager.transferClient(dto, obj)),
                () -> assertEquals(emp, obj.getClient())
        );

    }

    @Test
    @DisplayName("should throw an exception when client invalid")
    void transferClient1() {
        when(clientDao.getById(anyInt())).thenReturn(null);
        assertThrows(InvalidDtoException.class, () -> manager.transferClient(dto, obj));
    }

    @Test
    @DisplayName("should transferCar")
    void transferCar() {
        Car emp = new Car();
        when(carDao.getCarByRegistration(anyString())).thenReturn(emp);

        assertAll(
                () -> assertDoesNotThrow(() -> manager.transferCar(dto, obj)),
                () -> assertEquals(emp, obj.getCar())
        );

    }

    @Test
    @DisplayName("should throw an exception when car invalid")
    void transferCar1() {
        when(carDao.getById(anyInt())).thenReturn(null);
        assertThrows(InvalidDtoException.class, () -> manager.transferCar(dto, obj));
    }

    @Test
    @DisplayName("should throw an exception when DateReparation invalid")
    void transferDateReparation() {
        dto.setDateReparation("10-04-2020 18:26");
        assertDoesNotThrow(() -> manager.transferDateReparation(dto, obj));
    }

    @Test
    @DisplayName("should throw an exception when DateReparation invalid")
    void transferDateReparation1() {
        dto.setDateReparation("22/33/4444");
        assertThrows(InvalidDtoException.class, () -> manager.transferDateReparation(dto, obj));
    }

    @Test
    @DisplayName("should do nothing when DateReparation invalid")
    void transferDateReparation2() {
        dto.setDateReparation(null);
        assertDoesNotThrow(() -> manager.transferDateReparation(dto, obj));
    }


    @Test
    @DisplayName("should transferPieces")
    void transferPieces() {
        when(pieceDao.getById(anyInt())).thenReturn(new Piece());
        assertDoesNotThrow(() -> manager.transferPieces(dto, obj));

    }

    @Test
    @DisplayName("should throw an exception when piece invalid")
    void transferPieces1() {
        when(pieceDao.getById(anyInt())).thenReturn(null);
        assertThrows(InvalidDtoException.class, () -> manager.transferPieces(dto, obj));
    }

    @Test
    @DisplayName("should do nothing if pieces null")
    void transferPieces2() {
        dto.setPieces(null);
        assertDoesNotThrow(() -> manager.transferPieces(dto, obj));
    }

    @Test
    @DisplayName("should do nothing if pieces empty")
    void transferPieces3() {
        dto.setPieces(new ArrayList<>());
        assertDoesNotThrow(() -> manager.transferPieces(dto, obj));
    }

    @Test
    @DisplayName("should transferTasks")
    void transferTasks() {
        when(taskDao.getById(anyInt())).thenReturn(new Task());
        assertDoesNotThrow(() -> manager.transferTasks(dto, obj));

    }

    @Test
    @DisplayName("should throw an exception when task invalid")
    void transferTasks1() {
        when(taskDao.getById(anyInt())).thenReturn(null);
        assertThrows(InvalidDtoException.class, () -> manager.transferTasks(dto, obj));
    }

    @Test
    @DisplayName("should do nothing if tasks null")
    void transferTasks2() {
        dto.setTasks(null);
        assertDoesNotThrow(() -> manager.transferTasks(dto, obj));
    }

    @Test
    @DisplayName("should do nothing if tasks empty")
    void transferTasks3() {
        dto.setTasks(new ArrayList<>());
        assertDoesNotThrow(() -> manager.transferTasks(dto, obj));
    }

    @Test
    @DisplayName("should update Stock")
    void updateStockAndAddPieces() {


    }
}
