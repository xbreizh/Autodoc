package com.autodoc.model.models.car;


import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "manufacturer")
public class Manufacturer {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @NotNull(message = "name should not be null")
    @Column(unique = true)
    @Size(min = 2, max = 50, message = "name should have between 2 and 50 characters")
    private String name;


    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @Transient
    private List<CarModel> carModels;


    public Manufacturer() {
    }


    public Manufacturer(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        int carModelSize = 0;
        if (carModels != null) carModelSize = carModels.size();
        return "Manufacturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", carModels=" + carModelSize +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public List<CarModel> getCarModels() {
        return carModels;
    }

    public void setCarModels(List<CarModel> carModels) {
        this.carModels = carModels;
    }
}
