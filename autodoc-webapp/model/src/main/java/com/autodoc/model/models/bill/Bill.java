package com.autodoc.model.models.bill;


import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.model.models.person.employee.Employee;
import com.autodoc.model.models.pieces.Piece;
import com.autodoc.model.models.tasks.Task;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@Getter
@Setter
public class Bill {


    private int id;
    private Date dateReparation;
    @NotNull
    private String status;
    @NotNull
    private Car car;
    @NotNull
    private Client client;
    @NotNull
    private Employee employee;
    @NotNull
    private  List<Task> tasks;
    private  List<Piece> pieces;
    @NotNull
    private double total;
    @NotNull
    private double vat;
    @NotNull
    private double discount;

    private String comments;

    public Bill() {
    }

    public Bill(@NotNull Date dateReparation, @NotNull String status, @NotNull Car car, @NotNull Employee employee, @NotNull Client client, @NotNull List<Task> tasks, @NotNull double total, @NotNull double vat, @NotNull double discount) {
        this.dateReparation = dateReparation;
        this.status = status;
        this.car = car;
        this.employee = employee;
        this.client = client;
        this.tasks = tasks;
        this.total = total;
        this.vat = vat;
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", dateReparation=" + dateReparation +
                ", status='" + status + '\'' +
                ", car=" + car +
                ", client=" + client +
                ", employee=" + employee +
                ", tasks=" + tasks +
                ", pieces=" + pieces +
                ", total=" + total +
                ", vat=" + vat +
                ", discount=" + discount +
                ", comments='" + comments + '\'' +
                '}';
    }
}
