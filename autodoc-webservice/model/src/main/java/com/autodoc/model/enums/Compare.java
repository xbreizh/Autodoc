package com.autodoc.model.enums;

public enum Compare {


    //numbers
    NUMBEREQUALS("Integer", "="), NUMBERNOTEQUALS("Integer", "!="),NUMBERBIGGERTHAN("Integer", ">"), NUMBERSMALLERTHAN("Integer", "<"),
    NUMBERLESSOREQUALS("Integer", "<="), NUMBERMOREOREQUALS("Integer", ">="),

    //strings
    STRINGEQUALS("String", "="), STRINGNOTEQUALS("String", "!="), STRINGDOESNOTCONTAIN("String", " not like "), STRINGCONTAINS("String"," like "),

    //dates
    DATEBEFORE("Date", "<"), DATEON("Date", "="), DATEAFTER("Date", ">");

    String type;
    String queryValue;
    Compare(String type, String queryValue){
        this.type=type;
        this.queryValue=queryValue;
    }
    public String getType(){
        return type;
    }
    public String getQueryValue(){
        return queryValue;
    }
}
