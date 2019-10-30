package com.autodoc.model.models.person.provider;

import com.autodoc.model.enums.SearchType;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "country")
@Setter
@Getter
@ToString
public class Country  {


    public static final Map<String, SearchType> SEARCH_FIELD = createMap();

    private static Map<String, SearchType> createMap() {
        Map<String, SearchType> result = new HashMap<>();
        result.put("name", SearchType.STRING);
        return Collections.unmodifiableMap(result);
    }


    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;


    @OneToMany(mappedBy = "country", cascade = CascadeType.REMOVE)
    private List<Address> addressList;

    @NonNull
    private String name;


}
