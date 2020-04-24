package com.autodoc.business.impl.person.employee;

import com.autodoc.business.contract.person.employee.EmployeeManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.dao.contract.person.employee.EmployeeDao;
import com.autodoc.model.dtos.RoleListDTO;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import com.autodoc.model.enums.Role;
import com.autodoc.model.models.employee.Employee;
import lombok.Builder;
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
@Builder
public class EmployeeManagerImpl extends AbstractGenericManager implements EmployeeManager {
    private static final Logger LOGGER = Logger.getLogger(EmployeeManagerImpl.class);
    private static final ModelMapper mapper = new ModelMapper();
    private EmployeeDao dao;

    public Class getEntityClass() {
        return Employee.class;
    }

    public Class getDtoClass() {
        return EmployeeDTO.class;
    }

    @Override
    public IGenericDao getDao() {
        LOGGER.info("getting dao: ");

        return dao;
    }

    @Override
    public boolean exist(String login) {
        return dao.getByLogin(login) != null;
    }


    @Override
    public EmployeeDTO entityToDto(Object entity) {
        LOGGER.info("converting: " + entity);
        EmployeeDTO dto = mapper.map(entity, EmployeeDTO.class);
        dto.setRoles(convertRoleFromEntityToDto(((Employee) entity).getRoles()));
        LOGGER.info("dto: " + dto);
        return dto;
    }

    @Override
    public Employee dtoToEntity(Object entity) {
        Employee employee = mapper.map(entity, Employee.class);
        EmployeeDTO dto = (EmployeeDTO) entity;
        if (dto.getRoles() != null) {
            employee.setRoles(convertRoleFromDtoToEntity(dto.getRoles()));
        }
        return employee;
    }

    public List<String> convertRoleFromEntityToDto(List<Role> roles) {
        List<String> roleString = new ArrayList<>();
        for (Role role : roles) {
            roleString.add(role.toString().toUpperCase());
        }
        return roleString;

    }


    @Override
    public Employee transferUpdate(Object obj) {
        LOGGER.info("transfer update: " + obj);
        EmployeeDTO dto = (EmployeeDTO) obj;
        if (dto.getId() == 0) throw new InvalidDtoException("invalid id: " + 0);
        Employee employee = (Employee) dao.getById(dto.getId());
        if (employee == null) return null;
        LOGGER.info("employee: " + employee);
        if (dto.getPhoneNumber() != null) employee.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getLogin() != null) employee.setLogin(dto.getLogin());
        if (dto.getFirstName() != null) employee.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) employee.setLastName(dto.getLastName());
        if (dto.getPassword() != null) employee.setPassword(dto.getPassword());
        if (dto.getStartDate() != null) employee.setStartDate(dto.getStartDate());
        checkAndPassLogin(employee, dto.getLogin().toUpperCase());
        if (dto.getRoles() != null) employee.setRoles(convertRoleFromDtoToEntity(dto.getRoles()));
        return employee;
    }

    public List<Role> convertRoleFromDtoToEntity(List<String> roles) {
        LOGGER.info("converting role to entity");
        List<Role> roleList = new ArrayList<>();
        for (String role : roles) {
            try {

                roleList.add(Role.valueOf(role.toUpperCase()));

            } catch (Exception e) {
                throw new InvalidDtoException("invalid role: " + role);
            }
        }
        return roleList;
    }


    public Employee transferInsert(Object obj) {
        EmployeeDTO dto = (EmployeeDTO) obj;
        Employee employee1 = dao.getByLogin(dto.getLogin().toUpperCase());
        LOGGER.info("employee1: " + employee1);
        if (dao.getByLogin(dto.getLogin()) != null) {
            throw new InvalidDtoException("That login is already used: " + dto.getLogin());
        }
        LOGGER.info("transferring data: " + dto);
        Employee employee = dtoToEntity(dto);
        checkAndPassLogin(employee, dto.getLogin().toUpperCase());

        employee.setRoles(convertRoleFromDtoToEntity(dto.getRoles()));
        employee.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));

        if (dto.getStartDate() == null) {
            employee.setStartDate(new Date());
        } else {
            employee.setStartDate(dto.getStartDate());
        }
        employee.setPhoneNumber(dto.getPhoneNumber());
        return employee;

    }

    public void checkAndPassLogin(Employee employee, String login) {
        Employee employee1 = dao.getByLogin(login.toUpperCase());
        LOGGER.info(employee);
        if (employee1 != null && employee.getId() != 0 && (employee1.getId() != employee.getId())) {
            String error = "Login " + login.toUpperCase() + "already exists";
            LOGGER.error(error);
            throw new InvalidDtoException(error);
        }

        employee.setLogin(login);
    }

    @Override
    public EmployeeDTO getEmployeeDtoByLogin(String login) {
        LOGGER.info("getting by login: " + login);
        Employee employee = dao.getByLogin(login);
        if (employee == null) return null;
        return entityToDto(dao.getByLogin(login));
    }

    @Override
    public EmployeeDTO getEmployeeDtoByToken(String token) {
        Employee employee = dao.getByToken(token);
        if (employee == null) return null;
        return entityToDto(employee);
    }

    @Override
    public Employee getEmployeeByLogin(String login) {
        LOGGER.info("login to find: " + login);
        return dao.getByLogin(login.toUpperCase());
    }

    @Override
    public Employee getByToken(String token) {
        return dao.getByToken(token);
    }

    @Override
    public List<EmployeeDTO> getByRoles(List<RoleListDTO> roles) {
        LOGGER.info("trying to get by role manager");
        List<Role> roleList = checkRoleValuesValid(roles);

        List<Employee> employeeList = dao.getByRole(roleList);

        List<EmployeeDTO> employeeDTOList = new ArrayList<>();

        for (Employee employee : employeeList) {
            EmployeeDTO dto = entityToDto(employee);
            employeeDTOList.add(dto);
        }

        return employeeDTOList;
    }

    public List<Role> checkRoleValuesValid(List<RoleListDTO> roles) {
        LOGGER.info("trying to validate roles: " + roles.size());
        if (roles.isEmpty()) throw new InvalidDtoException("no role provided");
        List<Role> roleList = new ArrayList<>();
        for (RoleListDTO role : roles) {
            boolean found = false;
            if (role == null) throw new InvalidDtoException("there shouldn't be any empty role");
            for (Role role1 : Role.values()) {
                if (role.getRole().equalsIgnoreCase(role1.name())) {
                    found = true;
                    LOGGER.info("found: " + role);
                    roleList.add(role1);
                }
            }
            if (!found) throw new InvalidDtoException("wrong value for role: " + role);
        }
        LOGGER.info("all roles are valid: " + roles);
        return roleList;
    }
}
