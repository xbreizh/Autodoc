package com.autodoc.business.impl.person.employee;

import com.autodoc.business.contract.person.employee.EmployeeManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.person.employee.EmployeeDaoImpl;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import com.autodoc.model.models.person.employee.Employee;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class EmployeeManagerImpl<T, D> extends AbstractGenericManager implements EmployeeManager {
    private ModelMapper mapper;
    private EmployeeDaoImpl employeeDao;
    private final static Logger LOGGER = Logger.getLogger(EmployeeManagerImpl.class);

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
        System.out.println("converting");
        EmployeeDTO dto = mapper.map(entity, EmployeeDTO.class);
        LOGGER.info("converted into dto");
        return dto;
    }

    @Override
    public Employee dtoToEntity(Object entity) throws Exception {
        EmployeeDTO dto = (EmployeeDTO) entity;
        Employee employee = mapper.map(entity, Employee.class);
        checkDataInsert(dto);
        return employee;
    }

    @Override
    public EmployeeDTO getEmployeeByLogin(String login) {
        return entityToDto(employeeDao.getByLogin(login));
    }

    @Override
    public EmployeeDTO getEmployeeByToken(String token) {
        return entityToDto(employeeDao.getByToken(token));
    }

    @Override
    public Employee getByLogin(String login) {
        return employeeDao.getByLogin(login);
    }

    @Override
    public Employee getByToken(String token) {
        return employeeDao.getByToken(token);
    }
}
