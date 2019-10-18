package com.autodoc.business.contract.person.employee;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.models.person.employee.Skill;

public interface SkillManager extends IGenericManager {


    String save(Skill skill);

    //  List<Skill> getAll();
}
