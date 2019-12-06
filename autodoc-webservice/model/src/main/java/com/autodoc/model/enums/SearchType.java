package com.autodoc.model.enums;

public enum SearchType {

    INTEGER(new String[]{"EQUALS", "="}, new String[]{"NOTEQUALS", "!="}, new String[]{"SMALLER", "<"}, new String[]{"BIGGER", ">"}),
    STRING(new String[]{"EQUALS", "="}, new String[]{"NOTEQUALS", "!="}, new String[]{"CONTAINS", "LIKE"}, new String[]{"DOESNOTCONTAIN", "NOT LIKE"}),
    DATE(new String[]{"BEFORE", "<"}, new String[]{"AFTER", ">"}, new String[]{"ON", "="}, new String[]{"NOTON", "NOT ON"}),
    BOOLEAN(new String[]{"EQUALS", "="});


    String[][] values;

    SearchType(String[]... values) {

        this.values = values;
    }

    public String[][] getValues() {
        return values;
    }
}
