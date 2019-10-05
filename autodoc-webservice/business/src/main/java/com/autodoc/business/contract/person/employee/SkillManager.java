package com.autodoc.business.contract.person.employee;

import com.autodoc.model.models.person.employee.Skill;

import java.util.List;

public interface SkillManager {


    String save(Skill skill);

    List<Skill> getAll();
}
