package com.autodoc.model.models.search;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
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
