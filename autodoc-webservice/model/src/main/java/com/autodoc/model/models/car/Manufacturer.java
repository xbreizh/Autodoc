package com.autodoc.model.models.car;


import lombok.NonNull;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "manufacturer")
public class Manufacturer /*implements Serializable */ {


    // Constructors

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @NonNull
    @Column(unique = true)
    private String name;

    // Parameters
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
        int carModelSize=0;
        if(carModels!=null)carModelSize=carModels.size();
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
