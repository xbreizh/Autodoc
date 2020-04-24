package com.autodoc.model.models.car;


import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.person.client.Client;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
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

    protected static final Map<String, SearchType> SEARCH_FIELD = createMap();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    /**
     * Acar registration
     */
    @Column(name = "registration", unique = true)
    @NotBlank(message = "registration cannot be null")
    @Pattern(regexp = "^[a-zA-Z0-9]{8,10}$", message = "invalid registration (letters, numbers, between 8 and 10)")
    private String registration;
    @NotNull(message = "carModel missing or invalid")
    @ManyToOne
    private CarModel carModel;
    @NotNull(message = "client missing or invalid")
    @ManyToOne
    private Client client;
    private String color;
    @Positive
    private double mileage;
    @OneToMany(mappedBy = "car", cascade = CascadeType.REMOVE)
    private List<Bill> bills;

    public static Map<String, SearchType> getSearchField() {
        return SEARCH_FIELD;
    }

    private static Map<String, SearchType> createMap() {
        Map<String, SearchType> result = new HashMap<>();
        result.put("carModel.id", SearchType.INTEGER);
        result.put("client.id", SearchType.INTEGER);
        result.put("ID", SearchType.INTEGER);
        return Collections.unmodifiableMap(result);
    }

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

    public void setColor(String color) {
        this.color = color.toUpperCase();
    }

    public static class CarBuilder {
        public CarBuilder registration(String registration) {
            this.registration = registration.toUpperCase();
            return this;
        }

        public CarBuilder color(String color) {
            this.color = color.toUpperCase();
            return this;
        }


    }


}
