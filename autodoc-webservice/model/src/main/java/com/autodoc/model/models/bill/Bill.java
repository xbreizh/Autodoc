package com.autodoc.model.models.bill;


import com.autodoc.model.enums.SearchType;
import com.autodoc.model.enums.Status;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.model.models.person.employee.Employee;
import com.autodoc.model.models.tasks.Task;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "bill")
@Getter
@Setter
public class Bill {

    public static Map<String, SearchType> getSearchField() {
        return SEARCH_FIELD;
    }

    public static final Map<String, SearchType> SEARCH_FIELD = createMap();

    private static Map<String, SearchType> createMap() {
        Map<String, SearchType> result = new HashMap<>();
        result.put("NAME", SearchType.STRING);
        result.put("ID", SearchType.INTEGER);
        return Collections.unmodifiableMap(result);
    }


    public Bill() {
    }

    public Bill(@NotNull Date date, @NotNull Status status, @NotNull Car car, @NotNull Employee employee,@NotNull Client client, @NotNull List<Task> tasks, @NotNull double total, @NotNull double vat, @NotNull double discount) {
        this.date = date;
        this.status = status;
        this.car = car;
        this.employee = employee;
        this.client = client;
        this.tasks = tasks;
        this.total = total;
        this.vat = vat;
        this.discount = discount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @NotNull
    private Date date;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    @ManyToOne
    private Car car;

    @NotNull
    @ManyToOne
    private Client client;


    @NotNull
    @ManyToOne
    private Employee employee;

    @NotNull
    @ManyToMany(cascade = CascadeType.REMOVE)
    private transient List<Task> tasks;

    @NotNull
    private double total;

    @NotNull
    private double vat;

    @NotNull
    private double discount;

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", date=" + date +
                ", status=" + status +
                ", total=" + total +
                ", vat=" + vat +
                ", discount=" + discount +
                '}';
    }
}
