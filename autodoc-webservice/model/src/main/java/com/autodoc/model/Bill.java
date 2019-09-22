package com.autodoc.model;


import com.autodoc.model.enums.Status;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

}
