package com.autodoc.controllers.impl.person.employee;


import com.autodoc.business.contract.IGenericManager;
import com.autodoc.business.contract.person.employee.EmployeeManager;
import com.autodoc.controllers.contract.person.employee.EmployeeController;
import com.autodoc.controllers.impl.GlobalControllerImpl;
import com.autodoc.model.dtos.RoleListDTO;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import com.autodoc.model.models.employee.Employee;
import lombok.Builder;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
@Builder
public class EmployeeControllerImpl extends GlobalControllerImpl<Employee, EmployeeDTO> implements EmployeeController {
    private static final Logger LOGGER = Logger.getLogger(EmployeeControllerImpl.class);
    private EmployeeManager manager;
    //   private GsonConverter converter;

    public IGenericManager getManager() {
        return manager;
    }

    @Override
    @GetMapping(value = "/name",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getByName(@RequestParam(value = "name") String name) {
        LOGGER.info("getting employee by name: " + name);
        Object received = manager.getEmployeeByLogin(name);
        if (received == null) return notFoundResponse;
        EmployeeDTO employee = manager.getEmployeeByLogin(name);
        String response = converter.convertObjectIntoGsonObject(employee);
        LOGGER.info("response: " + response);
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping(value = "/roles",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getByRoles(@RequestBody List<RoleListDTO> roles) throws Exception {
        LOGGER.info("trying to get by role: " + roles.get(0));
        List<EmployeeDTO> employees = manager.getByRoles(roles);
        String response = converter.convertObjectIntoGsonObject(employees);

        return ResponseEntity.ok(response);
    }


}
