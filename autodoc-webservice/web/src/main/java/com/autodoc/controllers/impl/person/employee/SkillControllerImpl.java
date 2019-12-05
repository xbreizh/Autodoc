/*
package com.autodoc.controllers.impl.person.employee;


import com.autodoc.business.contract.person.employee.SkillManager;
import com.autodoc.controllers.contract.person.employee.SkillController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.controllers.impl.GlobalControllerImpl;
import com.autodoc.model.dtos.person.employee.SkillDTO;
import com.autodoc.model.models.person.employee.Skill;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/skills")
public class SkillControllerImpl extends GlobalControllerImpl<Skill, SkillDTO> implements SkillController {
    private static final Logger LOGGER = Logger.getLogger(SkillControllerImpl.class);
    private SkillManager skillManager;
    private GsonConverter converter;

    public SkillControllerImpl(SkillManager skillManager) {
        super(skillManager);
        converter = new GsonConverter();
        this.skillManager = skillManager;
    }


    @Override
    public ResponseEntity getByName(String name) throws Exception {
        SkillDTO skillDTO = (SkillDTO) skillManager.getByName("name");
        String response = converter.convertObjectIntoGsonObject(skillDTO);

        return ResponseEntity.ok(response);
    }


}
*/
