package com.autodoc.model.models;

import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class EmployeeList {
    private List<EmployeeDTO> employees;

    public EmployeeList() {
        employees = new ArrayList<>();
    }


}