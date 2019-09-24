package com.autodoc.model;


import com.autodoc.model.enums.Status;
import com.autodoc.model.person.Client;
import com.autodoc.model.person.Employee;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bill")
@Getter @Setter @ToString
public class Bill implements Serializable {

    // Constructors

    public Bill() {
    }

    public Bill(Date date, Status status, Car car, Client client, Employee employee, List<Task> tasks, double total, double vat, double discount) {
        this.date = date;
        this.status = status;
        this.car = car;
        this.client = client;
        this.employee = employee;
        this.tasks = tasks;
        this.total = total;
        this.vat = vat;
        this.discount = discount;
    }

    // Parameters

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    private Date date;

    private Status status;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Employee employee;

    @ManyToMany
    private List<Task> tasks;

    private double total;

    private double vat;

    private double discount;


}
