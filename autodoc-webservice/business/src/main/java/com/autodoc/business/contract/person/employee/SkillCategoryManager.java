package com.autodoc.business.contract.person.employee;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.models.person.employee.SkillCategory;

public interface SkillCategoryManager extends IGenericManager {


    String save(SkillCategory skillCategory);

    //  List<SkillCategory> getAll();
}
