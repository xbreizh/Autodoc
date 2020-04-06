package com.autodoc.model.models.car;


import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.person.client.Client;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "car")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Cacheable
@Generated
public class Car {

    public static Map<String, SearchType> getSearchField() {
        return SEARCH_FIELD;
    }

    protected static final Map<String, SearchType> SEARCH_FIELD = createMap();

    private static Map<String, SearchType> createMap() {
        Map<String, SearchType> result = new HashMap<>();
        result.put("carModel.id", SearchType.INTEGER);
        result.put("client.id", SearchType.INTEGER);
        result.put("ID", SearchType.INTEGER);
        return Collections.unmodifiableMap(result);
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
    private List<Bill> bills;


    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", registration='" + registration + '\'' +
                ", carModel=" + carModel +
                ", client=" + client +
                '}';
    }

    public void setRegistration(String registration) {
        this.registration = registration.toUpperCase();
    }

    public static class CarBuilder {
        public CarBuilder registration(String registration) {
            this.registration = registration.toUpperCase();
            return this;
        }


    }


}
