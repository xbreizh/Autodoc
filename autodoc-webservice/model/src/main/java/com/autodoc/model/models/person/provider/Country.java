package com.autodoc.model.models.person.provider;

import com.autodoc.model.enums.SearchType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "country")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {


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


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;


    @OneToMany(mappedBy = "country", cascade = CascadeType.REMOVE)
    private List<Address> addressList;

    @NotNull(message = "name cannot be null")
    @Size(min = 2, max = 30, message = "name should have between {min} and {max} characters")
    private String name;

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public static class CountryBuilder {
        public CountryBuilder name(String name) {
            this.name = name.toUpperCase();
            return this;
        }


    }
}
