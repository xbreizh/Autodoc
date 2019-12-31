package com.autodoc.spring.controller.impl;

import com.autodoc.business.contract.CarManager;
import com.autodoc.business.contract.ClientManager;
import com.autodoc.business.contract.EmployeeManager;
import com.autodoc.helper.LibraryHelper;
import com.autodoc.model.dtos.RegistrationForm;
import com.autodoc.model.dtos.car.CarDTO;
import com.autodoc.model.dtos.car.CarForm;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.model.models.person.employee.Employee;
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
public class CarControllerImpl extends GlobalController<CarDTO, Car> implements CarController {

    private static Logger LOGGER = Logger.getLogger(CarControllerImpl.class);
    // @Inject
    CarManager manager;
    ClientManager clientManager;
    CarManager carManager;
    EmployeeManager employeeManager;

    public CarControllerImpl(LibraryHelper helper, CarManager manager, ClientManager clientManager, CarManager carManager, EmployeeManager employeeManager) {
        super(helper);
        this.manager = manager;
        this.clientManager = clientManager;
        this.carManager = carManager;
        this.employeeManager = employeeManager;
    }

    @PostMapping("/searchCar")
    public ModelAndView searchCar(@Valid CarForm  carForm, BindingResult bindingResult) throws Exception {
        LOGGER.info("getting herer: " + carForm);
        LOGGER.info("retrieving searchCar");
        String registration = carForm.getRegistration().toUpperCase();
        ModelAndView mv = checkAndAddConnectedDetails("operations");

        if (bindingResult.hasErrors()) {
            return mv;
        }


        Car car = carManager.getByRegistration(helper.getConnectedToken(), registration);
        LOGGER.info("car found: " + car);
        if (car == null) {
            //mv = checkAndAddEmployeeDetails("operations");
            mv.addObject("message", "no car found");
            return mv;
        }
        LOGGER.info("car found: " + registration);
        LOGGER.info("owner: " + car.getClient().getLastName());
        mv.addObject("car", car);
        mv.addObject("client", car.getClient());
        mv.addObject("model", car.getModel());
        return mv;


    }


    @GetMapping("")
    public ModelAndView cars() throws Exception {
        LOGGER.info("retrieving cars");
        ModelAndView mv = checkAndAddConnectedDetails("cars");
        List<Car> cars;
        try {
            cars = manager.getAll(helper.getConnectedToken());
            LOGGER.info("cars found: " + cars.size());
            if (cars.isEmpty()) {
                return sendError(mv, "no car found");
            }
            mv.addObject("cars", cars);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }


        return mv;

    }


    @GetMapping(value = "/{id}")
    @ResponseBody
    public ModelAndView carById(@PathVariable Integer id) throws Exception {
        LOGGER.info("trying to get member with id " + id);
        String token = helper.getConnectedToken();
        ModelAndView mv = checkAndAddConnectedDetails("cars_details");
        LOGGER.info("car is null");
        Car car = (Car) manager.getById(helper.getConnectedToken(), id);
        LOGGER.info("car: " + car);
        List<Employee> employees = employeeManager.getAll(token);
        List<Client> clients = clientManager.getAll(token);
        List<Car> cars = employeeManager.getAll(token);
        LOGGER.info("cars: " + cars.size());
        LOGGER.info("clients: " + clients.size());
        LOGGER.info("employees: " + employees.size());
        mv.addObject("employees", employees);
        mv.addObject("clients", clients);
        mv.addObject("cars", cars);
        mv.addObject("carForm", car);
        mv.addObject("showForm", 1);
        mv.addObject("car", car);
        return mv;
    }


    @PostMapping(value = "/update/{id}")
    @ResponseBody
    public ModelAndView update(@Valid CarForm carForm, BindingResult bindingResult) throws Exception {
        LOGGER.info("trying to update member with id " + carForm.getId());
        String token = helper.getConnectedToken();
        ModelAndView mv = checkAndAddConnectedDetails("cars_details");
        mv.addObject("carForm", new CarForm());
        if (bindingResult.hasErrors()) {
            LOGGER.error("binding has errors");
            Car car = (Car) manager.getById(token, carForm.getId());
            List<Employee> employees = employeeManager.getAll(token);
            List<Client> clients = clientManager.getAll(token);
            List<Car> cars = employeeManager.getAll(token);
            mv.addObject("employees", employees);
            mv.addObject("clients", clients);
            mv.addObject("cars", cars);
            mv.addObject("car", car);
            mv.addObject("carForm", carForm);
            mv.addObject("showForm", 0);
            return mv;
        }
        LOGGER.info("carrying on");
        LOGGER.info("car retrieved: " + carForm);
        manager.update(helper.getConnectedToken(), carForm);
        return new ModelAndView("redirect:" + "/cars/" + carForm.getId());
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
        ModelAndView mv = checkAndAddConnectedDetails("cars_new");
        mv.addObject("carForm", new CarForm());
        mv.addObject("showForm", 1);
        return mv;
    }


    @PostMapping(value = "/new")
    @ResponseBody
    public ModelAndView create(@Valid CarForm carForm, BindingResult bindingResult) throws Exception {
        LOGGER.info("trying to create member ");
        String token = helper.getConnectedToken();
        ModelAndView mv = checkAndAddConnectedDetails("cars_new");
        LOGGER.info("empl: " + carForm);
        mv.addObject("carForm", new CarForm());
        List<Employee> employees = employeeManager.getAll(token);
        List<Client> clients = clientManager.getAll(token);
        List<Car> cars = employeeManager.getAll(token);
        mv.addObject("employees", employees);
        mv.addObject("clients", clients);
        mv.addObject("cars", cars);
        if (bindingResult.hasErrors()) {
            LOGGER.error("binding has errors");
            mv.addObject("carForm", carForm);
            mv.addObject("showForm", 1);
            return mv;
        }
        LOGGER.info("car retrieved: " + carForm);
        manager.add(helper.getConnectedToken(), carForm);
        return cars();
    }

    private CarDTO convertFormIntoDto(CarForm carForm) {
        LOGGER.info("TODO");
        return null;
    }


}

