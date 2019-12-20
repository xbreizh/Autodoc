package com.autodoc.model.dtos.bill;

import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.model.models.person.employee.Employee;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class BillForm {


    private static final double VAT = 00.195;
    private int id;


    @FutureOrPresent(message = "date should nto be in the past")
    private Date date;

    @NotEmpty
    private String status;

    @NotNull
    private List<Integer> tasks;

    @NotEmpty(message = "car  cannot be null")
    private Car car;

    @Min(value = 1, message = "client cannot be null")
    private Client client;

    @Min(value = 1, message = "employee cannot be null")
    private Employee employee;

    @Min(value = 1, message = "VAT cannot be null")
    private double vat;

    @Min(value = 1, message = "total cannot be null")
    private double total;

    private double discount;


    @Override
    public String toString() {
        return "BillForm{" +
                "id=" + id +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", tasks=" + tasks +
                ", car=" + car +
                ", client=" + client +
                ", employee=" + employee +
                ", vat=" + vat +
                ", total=" + total +
                ", discount=" + discount +
                '}';
    }
}










