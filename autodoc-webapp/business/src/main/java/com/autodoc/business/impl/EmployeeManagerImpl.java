package com.autodoc.business.impl;

import com.autodoc.business.contract.EmployeeManager;
import com.autodoc.contract.EmployeeService;
import com.autodoc.contract.GlobalService;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import com.autodoc.model.dtos.person.employee.EmployeeForm;
import com.autodoc.model.models.person.employee.Employee;
import lombok.Builder;
import org.apache.log4j.Logger;

import javax.inject.Named;
import java.util.List;

@Named
@Builder
public class EmployeeManagerImpl extends GlobalManagerImpl<Employee, EmployeeDTO> implements EmployeeManager {

    private static final Logger LOGGER = Logger.getLogger(EmployeeManagerImpl.class);

    EmployeeService service;

    GlobalService getService() {
        return service;
    }

    @Override
    public Employee getByLogin(String token, String login) {
        LOGGER.info("trying to get by login: " + login);
        return dtoToEntity(token, service.getByName(token, login));
    }

    @Override
    public List<String> getRoles(String token) {
        return enumService.getAll(token, "roles");
    }

    public Employee dtoToEntity(String token, Object obj) {

        EmployeeDTO dto = (EmployeeDTO) obj;
        LOGGER.info("dto: " + dto);
        Employee employee = new Employee();
        int id = dto.getId();
        LOGGER.info("id: " + id);
        employee.setId(id);
        employee.setLogin(dto.getLogin());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setRoles(dto.getRoles());
        employee.setPhoneNumber(dto.getPhoneNumber());
        employee.setStartDate(dto.getStartDate());
        employee.setLastConnection(dto.getLastConnection());
        LOGGER.info("entity transferred: " + employee);

        return employee;
    }

    public EmployeeDTO formToDto(Object obj, String token) {
        LOGGER.info("stuff to update: " + obj);
        EmployeeForm dto = (EmployeeForm) obj;
        LOGGER.info("dto: " + dto);
        LOGGER.info(dto.getFirstName());
        EmployeeDTO employee = new EmployeeDTO();
        if (dto.getId() != 0) employee.setId(dto.getId());
        employee.setLogin(dto.getLogin());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setRoles(dto.getRoles());
        employee.setPhoneNumber(dto.getPhoneNumber());
        LOGGER.info("employee rfom db: " + employee.getPassword());
        if (dto.getPassword() != null) {
            employee.setPassword(dto.getPassword());
        }
        LOGGER.info("entity transferred: " + employee);
        return employee;
    }

}
