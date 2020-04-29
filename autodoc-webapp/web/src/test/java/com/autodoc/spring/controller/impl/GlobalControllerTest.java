package com.autodoc.spring.controller.impl;


import com.autodoc.helper.contract.BillPdfCreator;
import com.autodoc.helper.impl.BillPdfCreatorImpl;
import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.car.Manufacturer;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.model.models.person.employee.Employee;
import com.autodoc.model.models.pieces.Piece;
import com.autodoc.model.models.pieces.PieceType;
import com.autodoc.model.models.tasks.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalControllerTest {

    BillPdfCreator pdfCreator;
    Bill bill = new Bill();
    Manufacturer manufacturer = new Manufacturer();
    CarModel carModel = new CarModel();
    Car car = new Car();
    Client client = new Client();
    List<Task> tasks = new ArrayList<>();
    List<Piece> pieces = new ArrayList<>();
    Employee employee = new Employee();

    @BeforeEach
    void init() {
        pdfCreator = new BillPdfCreatorImpl();
        manufacturer.setName("VOLVO");
        carModel.setManufacturer(manufacturer);
        carModel.setEngine("2.0");
        carModel.setFuelType("DIESEL");
        carModel.setName("S40");
        client.setFirstName("JOHN");
        client.setLastName("BUTLER");
        client.setPhoneNumber("0684512578");
        employee.setLogin("ROMARIO");
        bill.setId(323);
        bill.setEmployee(employee);
        car.setClient(client);
        car.setModel(carModel);
        car.setRegistration("07D821922");
        bill.setCar(car);
        bill.setDateReparation(new Date());
        bill.setPaymentType("CASH");
        bill.setStatus("COMPLETED");
        bill.setVat(19.6);
        bill.setDiscount(00.00);
        bill.setComments("je suis un commentaire");
        Task task1 = new Task();
        task1.setName("vidange");
        task1.setEstimatedTime(0.5);
        Task task2 = new Task();
        task2.setName("changement ampoule");
        task2.setEstimatedTime(0.5);
        tasks.add(task1);
        tasks.add(task2);
        PieceType pieceType1 = new PieceType();
        PieceType pieceType2 = new PieceType();
        PieceType pieceType3 = new PieceType();
        pieceType1.setName("huile");
        pieceType2.setName("filtre");
        pieceType3.setName("ampoule");

        Piece piece1 = new Piece();
        Piece piece2 = new Piece();
        Piece piece3 = new Piece();
        piece1.setPieceType(pieceType1);
        piece1.setName("huile synthetique");
        piece1.setSellPrice(15);
        piece2.setPieceType(pieceType2);
        piece2.setName("filtre v2");
        piece2.setSellPrice(10.20);
        piece3.setPieceType(pieceType3);
        piece3.setName("ampoule 12V");
        piece3.setSellPrice(1.5);
        pieces.add(piece1);
        pieces.add(piece2);
        pieces.add(piece3);
        bill.setTasks(tasks);
        bill.setPieces(pieces);
        bill.setClient(client);
    }


    @Test
    void generatePDFFromHTML() throws IOException {
        System.out.println("ddd");
        pdfCreator.generatePDFFromHTML(bill);

    }

    @Test
    void copyFile() throws IOException {
        /*System.out.println("bills: "+ FileSystems.getDefault()
                .getPath("")
                .toAbsolutePath()
                .toString());
        String userDirectory = new File("").getAbsolutePath();
        System.out.println(userDirectory);*/
        //  Thread.currentThread().getContextClassLoader().get

        System.out.println();

    }

    @Test
    void checkIfFileExist() throws IOException, URISyntaxException {
        System.out.println(new File("test").getCanonicalPath());
        System.out.println(new File("test").getAbsolutePath());
     //   System.out.println("verdict: " + pdfCreator.checkIfFileExists("ter.txt"));
    }

    @Test
    void trim() {
        String str = "newFile.html";
        String str2 = str.substring(0, str.lastIndexOf('.'));
        System.out.println(str2);

    }

    @Test
    void cleanup() {
        String str = "[je ] ne participe [pas] au tele[thon]";
        assertEquals(" ne participe au tele ", pdfCreator.cleanup(str));

    }

    @Test
    void formatDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String dateString = format.format(new Date());
        System.out.println(dateString);

        String pattern = "EEEEE dd MMMMM yyyy HH:mm";
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(pattern, new Locale("fr", "FR"));

        String date = simpleDateFormat.format(new Date());
        System.out.println(date);
    }

}
