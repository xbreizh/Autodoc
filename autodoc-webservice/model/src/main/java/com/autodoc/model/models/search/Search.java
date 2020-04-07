package com.autodoc.model.models.search;

import lombok.Builder;
import lombok.Data;
import lombok.Generated;

import javax.validation.constraints.NotNull;

@Data
@Builder
@Generated
public class Search {


    @NotNull(message = "fieldName cannot be null")
    String fieldName;

    @NotNull(message = "compare cannot be null")
    String compare;

    @NotNull(message = "value cannot be null")
    String value;


    public Search(String fieldName, String compare, String value) {
        this.fieldName = fieldName;
        this.compare = compare;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Search{" +
                "fieldName='" + fieldName + '\'' +
                ", compare=" + compare +
                ", value='" + value + '\'' +
                '}';
    }
}
