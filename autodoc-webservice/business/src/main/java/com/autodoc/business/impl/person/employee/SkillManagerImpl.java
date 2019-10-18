package com.autodoc.business.impl.person.employee;

import com.autodoc.business.contract.person.employee.SkillManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.person.employee.EmployeeDaoImpl;
import com.autodoc.model.models.person.employee.Employee;
import com.autodoc.model.models.person.employee.Skill;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class SkillManagerImpl<T, D> extends AbstractGenericManager implements SkillManager {

    private EmployeeDaoImpl<Employee> employeeDao;

    public SkillManagerImpl(EmployeeDaoImpl<Employee> employeeDao) {
        super(employeeDao);
        this.employeeDao = employeeDao;
    }


    @Override
    public Object entityToDto(Object entity) {
        return null;
    }

    @Override
    public String save(Skill skill) {
        return null;
    }
}
