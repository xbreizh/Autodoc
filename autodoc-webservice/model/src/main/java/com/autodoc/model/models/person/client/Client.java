package com.autodoc.model.models.person.client;


import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.person.Person;
import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "client")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class Client extends Person {

    public static Map<String, SearchType> getSearchField() {
        return SEARCH_FIELD;
    }

    protected static final Map<String, SearchType> SEARCH_FIELD = createMap();

    private static Map<String, SearchType> createMap() {
        Map<String, SearchType> result = new HashMap<>();
        result.put("firstname", SearchType.STRING);
        result.put("lastname", SearchType.STRING);
        result.put("phoneNumber", SearchType.STRING);
        result.put("ID", SearchType.INTEGER);
        return Collections.unmodifiableMap(result);
    }


    @OneToMany(mappedBy = "client", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Car> cars;

    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Bill> bills;


    @Override
    public String toString() {
        int billSize = 0;
        if (bills!=null && !bills.isEmpty())billSize=bills.size();
        int carSize = 0;
        if (cars!=null && !cars.isEmpty())carSize=cars.size();
        return "Client{" +
                ", id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", billSize='" + billSize +
                ", carSize='" + carSize +
                '}';
    }

    @Builder
    public Client(int id, @NonNull String firstName, @NonNull String lastName, @NonNull String phoneNumber) {
        super(id, firstName.toUpperCase(), lastName.toUpperCase(), phoneNumber.toUpperCase());

    }


}
