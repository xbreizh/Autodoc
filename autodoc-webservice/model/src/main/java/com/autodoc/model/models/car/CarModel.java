package com.autodoc.model.models.car;

import com.autodoc.model.enums.FuelType;
import com.autodoc.model.enums.GearBox;
import com.autodoc.model.enums.SearchType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "carModel")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Cacheable
public class CarModel {

    public static Map<String, SearchType> getSearchField() {
        return SEARCH_FIELD;
    }

    public static final Map<String, SearchType> SEARCH_FIELD = createMap();

    private static Map<String, SearchType> createMap() {
        Map<String, SearchType> result = new HashMap<>();
        result.put("NAME", SearchType.STRING);
        result.put("ID", SearchType.INTEGER);
        result.put("manufacturer.name", SearchType.STRING);
        result.put("gearbox", SearchType.STRING);
        result.put("fuelType", SearchType.STRING);
        result.put("engine", SearchType.STRING);

        return Collections.unmodifiableMap(result);
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne
    @NotNull
    private Manufacturer manufacturer;

    @OneToMany(mappedBy = "carModel", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Car> cars;


    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GearBox gearbox;

    @NotNull
    private String engine;

    @NotNull
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @Override
    public String toString() {
        return "CarModel{" +
                "id=" + id +
                ", manufacturer=" + manufacturer +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", gearbox=" + gearbox +
                ", engine='" + engine + '\'' +
                ", fuelType=" + fuelType +
                '}';
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public void setDescription(String description) {
        this.description = description.toUpperCase();
    }

    public void setEngine(String engine) {
        this.engine = engine.toUpperCase();
    }


    public static class CarModelBuilder {
        public CarModelBuilder name(String name) {
            this.name = name.toUpperCase();
            return this;
        }

        public CarModelBuilder description(String description) {
            this.description = description.toUpperCase();
            return this;
        }

        public CarModelBuilder engine(String engine) {
            this.engine = engine.toUpperCase();
            return this;
        }


    }
}
