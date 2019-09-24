package com.autodoc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "country")
@Setter @Getter @ToString
public class Country implements Serializable {

    // Constructor


    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    // Parameters

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;


    @OneToMany(mappedBy = "country")
    private List<Address> addressList;

    private String name;
}
