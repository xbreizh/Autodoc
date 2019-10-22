package com.autodoc.business.impl.person.employee;

import com.autodoc.business.contract.person.employee.SkillManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.person.employee.EmployeeDaoImpl;
import com.autodoc.model.dtos.person.employee.SkillDTO;
import com.autodoc.model.models.person.employee.Skill;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class SkillManagerImpl<T, D> extends AbstractGenericManager implements SkillManager {

    private EmployeeDaoImpl employeeDao;

    public SkillManagerImpl(EmployeeDaoImpl employeeDao) {
        super(employeeDao);
        this.employeeDao = employeeDao;
    }


    @Override
    public SkillDTO entityToDto(Object entity) {
        return null;
    }

    @Override
    public Skill dtoToEntity(Object entity) {
        return null;
    }

}
