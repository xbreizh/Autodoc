package com.autodoc.model.models.search;

import com.autodoc.model.enums.Compare;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Search {


    String fieldName;

    Compare compare;

    String value;


    public Search(String fieldName, Compare compare, String value) {
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
