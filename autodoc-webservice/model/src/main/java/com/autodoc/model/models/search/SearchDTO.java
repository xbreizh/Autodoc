package com.autodoc.model.models.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class SearchDTO {


    @NotNull(message = "fieldName cannot be null")
    String fieldName;

    @NotNull(message = "compare cannot be null")
    String compare;

    @NotNull(message = "value cannot be null")
    String value;


    @Override
    public String toString() {
        return "Search{" +
                "fieldName='" + fieldName + '\'' +
                ", compare=" + compare +
                ", value='" + value + '\'' +
                '}';
    }


}
