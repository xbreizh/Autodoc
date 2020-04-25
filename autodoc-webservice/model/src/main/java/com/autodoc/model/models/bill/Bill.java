package com.autodoc.model.models.bill;


import com.autodoc.model.enums.PaymentType;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.enums.Status;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.employee.Employee;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.model.models.pieces.Piece;
import com.autodoc.model.models.tasks.Task;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "BILL")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class Bill {

    public static Map<String, SearchType> getSearchField() {
        return SEARCH_FIELD;
    }

    protected static final Map<String, SearchType> SEARCH_FIELD = createMap();

    private static Map<String, SearchType> createMap() {
        Map<String, SearchType> result = new HashMap<>();
        result.put("client.Id", SearchType.INTEGER);
        result.put("car.registration", SearchType.STRING);
        result.put("total", SearchType.INTEGER);
        result.put("status", SearchType.STRING);
        result.put("paymentType", SearchType.STRING);
        result.put("ID", SearchType.INTEGER);
        return Collections.unmodifiableMap(result);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date dateReparation;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    private PaymentType paymentType;

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


    @ManyToMany
    private List<Piece> pieces;

    @NotNull
    private double total;

    @NotNull
    private double vat;

    @Min(value = 0, message = "invalid value for discount")
    @Max(value = 100, message = "discount max is 100")
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
                ", paymentType=" + paymentType +
                ", vat=" + vat +
                ", discount=" + discount +
                ", comments='" + comments + '\'' +
                '}';
    }

    public void setComments(String comments) {
        this.comments = comments.toUpperCase();
    }

    public static class BillBuilder {
        public BillBuilder comments(String comments) {
            this.comments = comments.toUpperCase();
            return this;
        }


    }

}
