package com.autodoc.controllers.contract.bill;

import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.car.Car;
import org.springframework.http.ResponseEntity;

public interface BillController {


    ResponseEntity getAll();

    ResponseEntity getCarByRegistration(String registration);

    ResponseEntity getCarById(Integer id);

    ResponseEntity getBillByClientId(int clientId);

    ResponseEntity addBill(Bill bill);

    ResponseEntity updateCar(Car car);

    ResponseEntity updateCarClient(Integer carId, Integer clientId);

    ResponseEntity deleteCar(Integer carId);
}
