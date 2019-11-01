package com.autodoc.model.enums;

public enum SearchType {

    INTEGER(new String[]{"EQUALS", "="}, new String[]{"NOTEQUALS","!="}, new String[]{"SMALLER", "<"}, new String[]{"BIGGER", ">"}),
    STRING(new String[]{"EQUALS", "="}, new String[]{"NOTEQUALS","!="}, new String[]{"CONTAINS", "IN"}, new String[]{"DOESNOTCONTAIN", "NOT IN"}),
    DATE(new String[]{"BEFORE", "<"}, new String[]{"AFTER", ">"}, new String[]{"ON", "="}, new String[]{"NOTON", "NOT ON"});

    String[][] values;
    SearchType(String[]...values){

        this.values =  values;
    }
   public String[][] getValues(){
        return values;
   }
}
