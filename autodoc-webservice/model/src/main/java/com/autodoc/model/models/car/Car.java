package com.autodoc.model.models.car;


import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.person.client.Client;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "car")
public class Car  {

    public static Map<String, SearchType> getSearchField() {
        return SEARCH_FIELD;
    }

    public static final Map<String, SearchType> SEARCH_FIELD = createMap();

    private static Map<String, SearchType> createMap() {
        Map<String, SearchType> result = new HashMap<>();
        result.put("carModel.id", SearchType.INTEGER);
        result.put("ID", SearchType.INTEGER);
        return Collections.unmodifiableMap(result);
    }

    public Car() {
    }

    public Car(String registration, CarModel carModel, Client client) {
        this.registration = registration;
        this.carModel = carModel;
        this.client = client;
    }




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @NotNull(message = "registration missing")
    @Column(name = "registration", unique = true)
    private String registration;

    @NotNull(message = "carModel missing or invalid")
    @ManyToOne
    private CarModel carModel;

    @NotNull(message = "client missing or invalid")
    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "car", cascade = CascadeType.REMOVE)
    private transient List<Bill> bills;


    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", registration='" + registration + '\'' +
                ", carModel=" + carModel +
                ", client=" + client +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }
}
