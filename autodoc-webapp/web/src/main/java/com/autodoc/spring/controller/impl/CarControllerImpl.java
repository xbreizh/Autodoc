package com.autodoc.spring.controller.impl;

import com.autodoc.business.contract.CarManager;
import com.autodoc.business.contract.CarModelManager;
import com.autodoc.business.contract.ClientManager;
import com.autodoc.business.contract.EmployeeManager;
import com.autodoc.helper.LibraryHelper;
import com.autodoc.model.dtos.car.CarDTO;
import com.autodoc.model.dtos.car.CarForm;
import com.autodoc.model.dtos.car.SearchCarForm;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.spring.controller.contract.CarController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@ControllerAdvice
@RequestMapping("/cars")
public class CarControllerImpl extends GlobalController<Car, CarDTO, SearchCarForm> implements CarController {

    private static final String KEY_WORD = "cars";
    private static Logger LOGGER = Logger.getLogger(CarControllerImpl.class);

    ClientManager clientManager;
    CarManager carManager;
    EmployeeManager employeeManager;
    CarModelManager carModelManager;

    public CarControllerImpl(LibraryHelper helper, CarManager manager, ClientManager clientManager, CarManager carManager, EmployeeManager employeeManager, CarModelManager carModelManager) {
        super(helper);
        this.manager = manager;
        this.clientManager = clientManager;
        this.carManager = carManager;
        this.employeeManager = employeeManager;
        this.carModelManager = carModelManager;
    }

    @Override
    String getKeyWord() {
        return KEY_WORD;
    }

    @PostMapping("/searchCar")
    public ModelAndView searchCar(@Valid SearchCarForm form, BindingResult bindingResult) throws Exception {
        String token = helper.getConnectedToken();
        LOGGER.info("getting here: " + form);
        String registration = form.getRegistration().toUpperCase();
        ModelAndView mv = checkAndAddConnectedDetails("operations/operations");
        if (bindingResult.hasErrors()) {
            LOGGER.error(bindingResult.getFieldError().getRejectedValue());
            mv.addObject("registration", registration);
            return mv;
        }


        Car car = carManager.getByRegistration(helper.getConnectedToken(), registration);
        if (car == null) {
            mv.addObject("message", "Registration not found in the system: ");
            mv.addObject("registration", registration);
            mv.addObject("form", new CarForm());
            mv.addObject("models", carModelManager.getAll(token));
            List<Client> clients = clientManager.getAll(token);
            mv.addObject("clients", clients);
            return mv;
        }

        mv = getById(car.getId());
        return mv;


    }


    @GetMapping("")
    public ModelAndView cars() throws Exception {
        return getList();

    }


    @GetMapping(value = "/{id}")
    @ResponseBody
    public ModelAndView carById(@PathVariable Integer id) throws Exception {
        ModelAndView mv = getById(id);
        String token = helper.getConnectedToken();
        mv.addObject("clients", clientManager.getAll(token));
        return mv;
    }


    @PostMapping(value = "/update/{id}")
    @ResponseBody
    public ModelAndView update(@Valid SearchCarForm form, BindingResult bindingResult) throws Exception {
        LOGGER.info("trying to update car with id " + form.getId());
        String token = helper.getConnectedToken();
        if (form == null) form = new SearchCarForm();
        ModelAndView mv = updateObject(form, form.getId(), bindingResult);

        mv.addObject("carForm", form);

        List<Client> clients = clientManager.getAll(token);
        mv.addObject("clients", clients);
        return mv;
    }


    @GetMapping(value = "/delete/{id}")
    @ResponseBody
    public ModelAndView delete(@PathVariable Integer id) throws Exception {
        LOGGER.info("trying to delete member with id " + id);
        manager.delete(helper.getConnectedToken(), id);
        return cars();
    }

    @GetMapping(value = "/new")
    public ModelAndView getCreate() {
        LOGGER.info("getting create form");
        return checkAndAddConnectedDetails("bills/bills_new");
    }

    @PostMapping(value = "/new")
    @ResponseBody
    public ModelAndView createNew(@Valid CarForm form, BindingResult bindingResult) throws Exception {
        LOGGER.info("carForm received: " + form);
        String token = helper.getConnectedToken();
        if (form == null) form = new CarForm();
        ModelAndView mv = checkAndAddConnectedDetails("operations/operations");
        if (bindingResult.hasErrors()) {
            mv.addObject("message", "registration not found in the system");
            mv.addObject("form", form);
            mv.addObject("models", carModelManager.getAll(token));
            LOGGER.error("error here");
            return mv;
        }
        String feedback = "";

        try {
            feedback = carManager.addNewCar(token, form);
            int id = Integer.parseInt(feedback);
            return new ModelAndView("redirect:" + "/" + KEY_WORD + "/" + id);
        } catch (NumberFormatException e) {
            LOGGER.error("bashing: " + e.getMessage());
            mv.addObject("form", form);
            mv.addObject("models", carModelManager.getAll(token));
            mv.addObject("error", feedback);
        }
        return mv;
    }



}

