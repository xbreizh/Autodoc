package com.autodoc.business.impl;

import com.autodoc.business.contract.EmployeeManager;
import com.autodoc.business.exceptions.ObjectFormattingException;
import com.autodoc.contract.EmployeeService;
import com.autodoc.contract.GlobalService;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import com.autodoc.model.dtos.person.employee.EmployeeForm;
import com.autodoc.model.models.person.employee.Employee;
import lombok.Builder;
import org.apache.log4j.Logger;

import javax.inject.Named;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        String date = dto.getStartDate();
        String dateFormatted = date.replaceAll(" .+$", "");
        employee.setStartDate(dateFormatted);
        employee.setLastConnection(dto.getLastConnection());
        LOGGER.info("entity transferred: " + employee);

        return employee;
    }

    public EmployeeDTO formToDto(Object obj, String token) throws Exception {
        LOGGER.info("stuff to update: " + obj);
        EmployeeForm form = (EmployeeForm) obj;
        LOGGER.info("form: " + form);
        LOGGER.info("date: " + form.getStartDate());
        EmployeeDTO dto = new EmployeeDTO();
        if (!checkIfDateIsValid(form.getStartDate())) {
            LOGGER.error("invalid date: " + form.getStartDate());
            throw new ObjectFormattingException("invalid date: " + form.getStartDate());
        }
        dto.setStartDate(form.getStartDate());
        if (form.getId() != 0) dto.setId(form.getId());
        dto.setLogin(form.getLogin());
        dto.setFirstName(form.getFirstName());
        dto.setLastName(form.getLastName());
        dto.setRoles(form.getRoles());
        dto.setPhoneNumber(form.getPhoneNumber());
        LOGGER.info("dto from db: " + dto.getPassword());
        if (form.getPassword() != null) {
            dto.setPassword(form.getPassword());
        }
        LOGGER.info("entity transferred: " + dto);
        return dto;
    }

    @Override
    public boolean checkIfDateIsValid(String stringDate) throws Exception {
        if (stringDate == null) {
            throw new ObjectFormattingException("date shouldn't be null");
        }
        System.out.println("reaching: " + stringDate);
        System.out.println("expected format: " + getDateFormat().toString());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);
        try {

            //if not valid, it will throw ParseException
            sdf.parse(stringDate);
            return true;

        } catch (ParseException e) {

            /*throw new ObjectFormattingException("invalid date: " + stringDate);*/
            return false;
        }
    }

}
