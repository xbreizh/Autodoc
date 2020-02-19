package com.autodoc.model.models.bill;


import com.autodoc.model.enums.SearchType;
import com.autodoc.model.enums.Status;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.employee.Employee;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.model.models.tasks.Task;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "BILL")
@Getter
@Setter
public class Bill {

    public static Map<String, SearchType> getSearchField() {
        return SEARCH_FIELD;
    }

    public static final Map<String, SearchType> SEARCH_FIELD = createMap();

    private static Map<String, SearchType> createMap() {
        Map<String, SearchType> result = new HashMap<>();
        result.put("client.Id", SearchType.INTEGER);
        result.put("car.registration", SearchType.STRING);
        result.put("total", SearchType.INTEGER);
        result.put("status", SearchType.STRING);
        result.put("ID", SearchType.INTEGER);
        return Collections.unmodifiableMap(result);
    }


    public Bill() {
    }

    public Bill(@NotNull Date dateReparation, @NotNull Status status, @NotNull Car car, @NotNull Employee employee, @NotNull Client client, @NotNull List<Task> tasks, @NotNull double total, @NotNull double vat, @NotNull double discount) {
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private Date dateReparation;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Car car;

    @NotNull
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Client client;


    @NotNull
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Employee employee;

    @NotNull
    @ManyToMany
    private List<Task> tasks;

    @NotNull
    private double total;

    @NotNull
    private double vat;

    @NotNull
    private double discount;

    private String comments;

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", dateReparation=" + dateReparation +
                ", status=" + status +
                ", car=" + car +
                ", client=" + client +
                ", employee=" + employee +
                ", tasks=" + tasks +
                ", total=" + total +
                ", vat=" + vat +
                ", discount=" + discount +
                ", comments='" + comments + '\'' +
                '}';
    }
}
