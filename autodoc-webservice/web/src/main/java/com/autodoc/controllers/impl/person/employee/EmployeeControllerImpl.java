package com.autodoc.controllers.impl.person.employee;


import com.autodoc.business.contract.person.employee.EmployeeManager;
import com.autodoc.controllers.contract.person.employee.EmployeeController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.controllers.impl.GlobalControllerImpl;
import com.autodoc.model.dtos.person.employee.EmployeeDTO;
import com.autodoc.model.models.person.employee.Employee;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employees")
public class EmployeeControllerImpl extends GlobalControllerImpl<Employee, EmployeeDTO> implements EmployeeController {
    private static final Logger LOGGER = Logger.getLogger(EmployeeControllerImpl.class);
    private EmployeeManager employeeManager;
    private GsonConverter converter;

    public EmployeeControllerImpl(EmployeeManager employeeManager) {
        super(employeeManager);
        converter = new GsonConverter();
        this.employeeManager = employeeManager;
    }


    @Override
    public ResponseEntity getByName(String name) {
        EmployeeDTO employee = employeeManager.getEmployeeByLogin("name");
        String response = converter.convertObjectIntoGsonObject(employee);

        return ResponseEntity.ok(response);
    }


}
