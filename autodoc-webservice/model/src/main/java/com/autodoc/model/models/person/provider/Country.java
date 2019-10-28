package com.autodoc.model.models.person.provider;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "country")
@Setter
@Getter
@ToString
public class Country {

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


    @OneToMany(mappedBy = "country", cascade = CascadeType.REMOVE)
    private List<Address> addressList;

    @NonNull
    private String name;
}
