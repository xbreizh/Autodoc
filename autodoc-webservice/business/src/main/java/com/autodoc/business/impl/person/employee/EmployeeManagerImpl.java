package com.autodoc.business.impl.person.employee;

import com.autodoc.business.contract.person.employee.EmployeeManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.person.employee.EmployeeDaoImpl;
import com.autodoc.model.dtos.RoleListDTO;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import com.autodoc.model.enums.Role;
import com.autodoc.model.models.employee.Employee;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Component
public class EmployeeManagerImpl<T, D> extends AbstractGenericManager implements EmployeeManager {
    private final static Logger LOGGER = Logger.getLogger(EmployeeManagerImpl.class);
    private ModelMapper mapper;
    private EmployeeDaoImpl employeeDao;

    public EmployeeManagerImpl(EmployeeDaoImpl employeeDao) {
        super(employeeDao);
        this.mapper = new ModelMapper();
        this.employeeDao = employeeDao;
    }


    @Override
    public boolean exist(String login) {
        return employeeDao.getByLogin(login) != null;
    }


    @Override
    public EmployeeDTO entityToDto(Object entity) {
        LOGGER.info("converting: " + entity);
        EmployeeDTO dto = mapper.map(entity, EmployeeDTO.class);
        dto.setFirstName(((Employee) entity).getFirstName());
        dto.setRoles(convertRoleFromEntityToDto(((Employee) entity).getRoles()));
        dto.setLastName(((Employee) entity).getLastName());
        dto.setPhoneNumber1(((Employee) entity).getPhoneNumber1());
        LOGGER.info("dto: " + dto);
        LOGGER.info("converted into dto");
        return dto;
    }

    private List<String> convertRoleFromEntityToDto(List<Role> roles) {
        List<String> roleString = new ArrayList<>();
        if (!roles.isEmpty()) {

        }
        for (Role role : roles) {
            roleString.add(role.toString());
        }
        return roleString;

    }

    @Override
    public Employee dtoToEntity(Object entity) throws Exception {
        EmployeeDTO dto = (EmployeeDTO) entity;
        checkDataInsert(dto);
        Employee employee = mapper.map(entity, Employee.class);
        return employee;
    }


    @Override
    public Employee transferUpdate(Object obj) throws Exception {
        LOGGER.info("transfer update: " + obj);
        EmployeeDTO dto = (EmployeeDTO) obj;
        if (dto.getId() == 0) throw new Exception("invalid id: " + 0);
        Employee employee = (Employee) employeeDao.getById(dto.getId());
        if (employee == null) return null;
        if (dto.getLogin() != null) employee.setLogin(dto.getLogin().toUpperCase());
        if (dto.getFirstName() != null) employee.setFirstName(dto.getFirstName().toUpperCase());
        if (dto.getLastName() != null) employee.setLastName(dto.getLastName().toUpperCase());
        if (dto.getPhoneNumber1() != null) employee.setPhoneNumber1(dto.getPhoneNumber1().toUpperCase());
        if (dto.getRoles() != null) employee.setRoles(convertRoleFromDtoToEntity(dto.getRoles()));

        return employee;
    }

    private List<Role> convertRoleFromDtoToEntity(List<String> roles) throws Exception {
        List<Role> roleList = new ArrayList<>();
        for (String role : roles) {
            try {

                roleList.add(Role.valueOf(role.toUpperCase()));

            } catch (Exception e) {
                throw new Exception("invalid role: " + role);
            }
        }
        return roleList;
    }


    public Employee transferInsert(Object obj) throws Exception {
        EmployeeDTO dto = (EmployeeDTO) obj;
        Employee employee1 = employeeDao.getByLogin(dto.getLogin().toUpperCase());
        LOGGER.info("employee1: " + employee1);
        if (employeeDao.getByLogin(dto.getLogin()) != null) {
            throw new Exception("That login is already used: " + dto.getLogin());
        }
        LOGGER.info("transferring data: " + dto);
      /*  dto.setId(0);
        LOGGER.info("transferring data: " + dto);*/
        checkDataInsert(dto);
        Employee employee = new Employee();
        employee.setLogin(dto.getLogin().toUpperCase());
        employee.setFirstName(dto.getFirstName().toUpperCase());
        employee.setLastName(dto.getLastName().toUpperCase());
        employee.setRoles(convertRoleFromDtoToEntity(dto.getRoles()));
        employee.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        employee.setStartDate(new Date());
        employee.setPhoneNumber1(dto.getPhoneNumber1());
        employee.setLogin(dto.getLogin());
        return employee;

    }

    @Override
    public EmployeeDTO getEmployeeByLogin(String login) {
        LOGGER.info("getting by login: " + login);
        Employee employee = employeeDao.getByLogin(login);
        if (employee == null) return null;
        return entityToDto(employeeDao.getByLogin(login));
    }

    @Override
    public EmployeeDTO getEmployeeByToken(String token) {
        return entityToDto(employeeDao.getByToken(token));
    }

    @Override
    public Employee getByLogin(String login) {

        login = login.toUpperCase();
        LOGGER.info("login to find: " + login);
        return employeeDao.getByLogin(login);
    }

    @Override
    public Employee getByToken(String token) {
        return employeeDao.getByToken(token);
    }

    @Override
    public List<EmployeeDTO> getByRoles(List<RoleListDTO> roles) throws Exception {
        LOGGER.info("trying to get by role manager");
        checkRoleValuesValid(roles);
        List<Role> roleList = checkRoleValuesValid(roles);
        /*for (RoleListDTO role: roles){
            roleList.add(role.getRole());
        }*/
        List<Employee> employeeList = employeeDao.getByRole(roleList);
        LOGGER.info("role: " + roleList.get(0));
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        for (Employee employee : employeeList) {
            // Employee employee = (Employee)obj;
            EmployeeDTO dto = entityToDto(employee);
            employeeDTOList.add(dto);
        }
        LOGGER.info("size found: " + employeeDTOList.size());
        LOGGER.info(employeeDTOList.get(0));

        return employeeDTOList;
    }

    public List<Role> checkRoleValuesValid(List<RoleListDTO> roles) throws Exception {
        LOGGER.info("trying to validate roles: " + roles.size());
        if (roles.isEmpty()) throw new Exception("no role provided");
        List<Role> roleList = new ArrayList<>();
        for (RoleListDTO role : roles) {
            boolean found = false;
            for (Role role1 : Role.values()) {
                if (role.getRole() == null) throw new Exception("there shouldn't be any empty role");
                if (role.getRole().equalsIgnoreCase(role1.name())) {
                    found = true;
                    LOGGER.info("found: " + role);
                    roleList.add(role1);
                }
            }
            if (!found) throw new Exception("wrong value for role: " + role);
        }
        LOGGER.info("all roles are valid: " + roles);
        return roleList;
    }
}
