package com.autodoc.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "client")
public class Client extends Person {

    @OneToMany(mappedBy = "client")
    private List<Car> cars;

    @OneToMany(mappedBy = "client")
    private List<Bill> bills;

}
