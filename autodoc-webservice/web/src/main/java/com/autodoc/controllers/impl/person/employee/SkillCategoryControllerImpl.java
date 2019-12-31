/*
package com.autodoc.controllers.impl.person.employee;


import com.autodoc.business.contract.person.employee.SkillCategoryManager;
import com.autodoc.controllers.contract.person.employee.SkillCategoryController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.controllers.impl.GlobalControllerImpl;
import com.autodoc.model.dtos.person.employee.SkillCategoryDTO;
import com.autodoc.model.models.employee.SkillCategory;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/skillCategories")
public class SkillCategoryControllerImpl extends GlobalControllerImpl<SkillCategory, SkillCategoryDTO> implements SkillCategoryController {
    private static final Logger LOGGER = Logger.getLogger(SkillCategoryControllerImpl.class);
    private SkillCategoryManager skillCategoryManager;
    private GsonConverter converter;

    public SkillCategoryControllerImpl(SkillCategoryManager skillCategoryManager) {
        super(skillCategoryManager);
        converter = new GsonConverter();
        this.skillCategoryManager = skillCategoryManager;
    }


    @Override
    public ResponseEntity getByName(String name) throws Exception {
        SkillCategoryDTO skillCategoryDTO = (SkillCategoryDTO) skillCategoryManager.getByName("name");
        String response = converter.convertObjectIntoGsonObject(skillCategoryDTO);

        return ResponseEntity.ok(response);
    }


}
*/
