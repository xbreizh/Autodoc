package com.autodoc.model.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SearchDto {


    @NotNull(message = "fieldName cannot be null")
    String fieldName;

    @NotNull(message = "compare cannot be null")
    String compare;

    @NotNull(message = "value cannot be null")
    String value;


    public SearchDto(String fieldName, String compare, String value) {
        this.fieldName = fieldName;
        this.compare = compare;
        this.value = value;
    }

    public SearchDto() {
    }

    @Override
    public String toString() {
        return "[\n" +
                "\t{\n" +
                "\t\t\"fieldName\":\"" + fieldName + "\",\n" +
                "\t\t\"compare\":\"" + compare + "\",\n" +
                "\t\t\"value\":\"" + value + "\"\n" +
                "}\n" +
                "\n" +
                "]";
    }
}
