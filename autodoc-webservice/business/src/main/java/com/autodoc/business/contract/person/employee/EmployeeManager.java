package com.autodoc.business.contract.person.employee;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.dtos.RoleListDTO;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import com.autodoc.model.enums.Role;
import com.autodoc.model.models.employee.Employee;

import java.util.List;

public interface EmployeeManager extends IGenericManager {

    boolean exist(String login);


    EmployeeDTO getEmployeeDtoByLogin(String login);

    EmployeeDTO getEmployeeDtoByToken(String token);

    Employee getEmployeeByLogin(String login);

    Employee getByToken(String token);

    List<EmployeeDTO> getByRoles(List<RoleListDTO> roles);

    List<Role> checkRoleValuesValid(List<RoleListDTO> roles);

    List<Role> convertRoleFromDtoToEntity(List<String> roles);

    List<String> convertRoleFromEntityToDto(List<Role> roles);

    void checkAndPassLogin(Employee employee, String login);

}
