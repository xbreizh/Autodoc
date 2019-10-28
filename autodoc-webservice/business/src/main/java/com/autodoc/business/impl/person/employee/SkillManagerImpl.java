package com.autodoc.business.impl.person.employee;

import com.autodoc.business.contract.person.employee.SkillManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.person.employee.EmployeeDaoImpl;
import com.autodoc.model.dtos.person.employee.SkillDTO;
import com.autodoc.model.models.person.employee.Skill;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class SkillManagerImpl<T, D> extends AbstractGenericManager implements SkillManager {

    private static final Logger LOGGER = Logger.getLogger(SkillManagerImpl.class);
    private EmployeeDaoImpl employeeDao;
    private ModelMapper mapper;

    public SkillManagerImpl(EmployeeDaoImpl employeeDao) {
        super(employeeDao);
        this.mapper = new ModelMapper();
        this.employeeDao = employeeDao;
    }


    @Override
    public SkillDTO entityToDto(Object entity) {
        SkillDTO dto = mapper.map(entity, SkillDTO.class);
        LOGGER.info("converted into dto");
        return dto;
    }

    @Override
    public Skill dtoToEntity(Object entity) throws Exception {
        SkillDTO dto = (SkillDTO) entity;
        Skill skill = mapper.map(entity, Skill.class);
        checkDataInsert(dto);
        return skill;
    }

}
