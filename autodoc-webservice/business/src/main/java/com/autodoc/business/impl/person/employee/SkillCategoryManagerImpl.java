/*
package com.autodoc.business.impl.person.employee;

import com.autodoc.business.contract.person.employee.SkillCategoryManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.person.employee.SkillCategoryDaoImpl;
import com.autodoc.model.dtos.person.employee.SkillCategoryDTO;
import com.autodoc.model.models.employee.SkillCategory;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class SkillCategoryManagerImpl<T, D> extends AbstractGenericManager implements SkillCategoryManager {

    private static final Logger LOGGER = Logger.getLogger(SkillCategoryManagerImpl.class);
    private SkillCategoryDaoImpl<SkillCategory> skillCategoryDao;
    private ModelMapper mapper;

    public SkillCategoryManagerImpl(SkillCategoryDaoImpl<SkillCategory> skillCategoryDao) {
        super(skillCategoryDao);
        this.mapper = new ModelMapper();
        this.skillCategoryDao = skillCategoryDao;
    }


    @Override
    public SkillCategoryDTO entityToDto(Object entity) {
        SkillCategoryDTO dto = mapper.map(entity, SkillCategoryDTO.class);
        LOGGER.info("converted into dto");
        return dto;
    }

    @Override
    public SkillCategory dtoToEntity(Object entity) throws Exception {
        SkillCategoryDTO dto = (SkillCategoryDTO) entity;
        SkillCategory skillCategory = mapper.map(entity, SkillCategory.class);
        checkDataInsert(dto);
        return skillCategory;
    }
}
*/
