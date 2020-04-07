package com.autodoc.model.models.car;


import com.autodoc.model.enums.SearchType;
import lombok.*;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "manufacturer")
@Cacheable
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class Manufacturer {

    public static Map<String, SearchType> getSearchField() {
        return SEARCH_FIELD;
    }

    protected static final Map<String, SearchType> SEARCH_FIELD = createMap();

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

    @NotNull(message = "name should not be null")
    @Column(unique = true)
    @Size(min = 2, max = 50, message = "name should have between 2 and 50 characters")
    private String name;


    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<CarModel> carModels;


    public static class ManufacturerBuilder {
        public ManufacturerBuilder name(String name) {
            this.name = name.toUpperCase();
            return this;
        }


    }

    @Override
    public String toString() {
        int carModelSize = 0;
        /* if (carModels != null) carModelSize = carModels.size();*/
        return "Manufacturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", carModels=" + carModelSize +
                '}';
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }


    @JsonIgnore
    public List<CarModel> getCarModels() {
        return carModels;
    }

    public void setCarModels(List<CarModel> carModels) {
        this.carModels = carModels;
    }


}
