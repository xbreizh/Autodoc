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
import com.autodoc.model.models.person.client.ClientList;
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
public class CarControllerImpl extends GlobalController<Car, CarDTO, CarForm> implements CarController {

    private static final String KEY_WORD = "cars";
    private static Logger LOGGER = Logger.getLogger(CarControllerImpl.class);
    // @Inject
    //  CarManager manager;
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
        LOGGER.info("getting here: " + form);
        String registration = form.getRegistration().toUpperCase();
        ModelAndView mv = checkAndAddConnectedDetails("operations/operations");
        String token = helper.getConnectedToken();
        if (bindingResult.hasErrors()) {
            LOGGER.error(bindingResult.getFieldError().getRejectedValue());
            return mv;
        }


        Car car = carManager.getByRegistration(helper.getConnectedToken(), registration);
        if (car == null) {
            mv.addObject("message", "Registration not found in the system: ");
            mv.addObject("registration", registration);
            mv.addObject("form", new CarForm());
            mv.addObject("models", carModelManager.getAll(token));
            return mv;
        }

        mv = getById(car.getId());
        List<Client> clients = clientManager.getAll(token);
        mv.addObject("clients", clients);
        ClientList clientList = new ClientList(clients);
        mv.addObject("clientList", clientList);
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
    public ModelAndView update(@Valid SearchCarForm searchCarForm, BindingResult bindingResult) throws Exception {
        LOGGER.info("trying to update car with id " + searchCarForm.getId());
        String token = helper.getConnectedToken();
        ModelAndView mv = checkAndAddConnectedDetails("cars/cars_details");

       // LOGGER.info("carform: " + searchCarForm);
        if (searchCarForm == null) searchCarForm = new SearchCarForm();
        mv.addObject("carForm", searchCarForm);

        if (bindingResult.hasErrors()) {
            LOGGER.error("binding has errors");
            LOGGER.error("error: " + bindingResult.getFieldError());
            Car car = (Car) manager.getById(token, searchCarForm.getId());
            //List<Employee> employees = employeeManager.getAll(token);
            List<Client> clients = clientManager.getAll(token);
            // List<Car> cars = employeeManager.getAll(token);
            //mv.addObject("employees", employees);
            mv.addObject("clients", clients);
            Client test = clients.get(1);
            mv.addObject("test", test);
            LOGGER.info("test: " + test);
            //  mv.addObject("cars", cars);
            mv.addObject("car", car);
            mv.addObject("carForm", searchCarForm);
            mv.addObject("showForm", 0);
            //  mv.addObject("currentClient", car.getClient().getId());
            return mv;
        }
        //  LOGGER.info("carrying on");
        LOGGER.info("car retrieved: " + searchCarForm);
        manager.update(helper.getConnectedToken(), searchCarForm);
        return new ModelAndView("redirect:" + "/cars/" + searchCarForm.getId());
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
        ModelAndView mv = checkAndAddConnectedDetails("bills/bills_new");
        mv.addObject("carForm", new SearchCarForm());
        mv.addObject("showForm", 1);
        return mv;
    }

    @PostMapping(value = "/newCar")
    @ResponseBody
    public ModelAndView createNew(@Valid CarForm carForm, BindingResult bindingResult) throws Exception {
        LOGGER.info("carForm received: " + carForm);
        String token = helper.getConnectedToken();

        ModelAndView mv = checkAndAddConnectedDetails("operations/operations");
        if (bindingResult.hasErrors()) {
            mv.addObject("message", "registration not found in the system");
            mv.addObject("carForm", new CarForm());
            mv.addObject("models", carModelManager.getAll(token));
            return mv;
        }



        int id = Integer.parseInt(carManager.addNewCar(token, carForm));

        return new ModelAndView("redirect:" + "/cars" + "/" + id);
    }


    @PostMapping(value = "/new")
    @ResponseBody
    public ModelAndView create(@Valid SearchCarForm searchCarForm, BindingResult bindingResult) throws Exception {
        LOGGER.info("trying to create member ");
        String token = helper.getConnectedToken();
        ModelAndView mv = checkAndAddConnectedDetails("cars/cars_new");
        LOGGER.info("empl: " + searchCarForm);
        mv.addObject("carForm", new SearchCarForm());
        List<Employee> employees = employeeManager.getAll(token);
        List<Client> clients = clientManager.getAll(token);
        List<Car> cars = carManager.getAll(token);
        mv.addObject("employees", employees);
        mv.addObject("clients", clients);
        mv.addObject("cars", cars);
        if (bindingResult.hasErrors()) {
            LOGGER.error("binding has errors");
            mv.addObject("carForm", searchCarForm);
            mv.addObject("showForm", 1);
            return mv;
        }
        LOGGER.info("car retrieved: " + searchCarForm);
        manager.add(helper.getConnectedToken(), searchCarForm);
        return new ModelAndView("redirect:/cars");
    }

/*
    private CarDTO convertFormIntoDto(SearchCarForm searchCarForm) {
        LOGGER.info("TODO");
        return null;
    }
*/


}

