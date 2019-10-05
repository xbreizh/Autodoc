package com.autodoc.business.contract.person.employee;

import com.autodoc.model.models.person.employee.SkillCategory;

import java.util.List;

public interface SkillCategoryManager {


    String save(SkillCategory skillCategory);

    List<SkillCategory> getAll();
}
