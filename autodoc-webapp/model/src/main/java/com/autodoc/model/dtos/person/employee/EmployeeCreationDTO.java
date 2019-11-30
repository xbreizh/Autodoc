package com.autodoc.model.dtos.person.employee;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmployeeCreationDTO {

    private List<EmployeeDTO> employeeDTOS;

    public void addEmployeeDTO(EmployeeDTO dto) {
        this.employeeDTOS = employeeDTOS;
    }
}
