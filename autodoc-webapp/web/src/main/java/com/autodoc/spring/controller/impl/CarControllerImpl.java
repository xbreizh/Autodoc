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
public class CarControllerImpl extends GlobalController<CarDTO, Car> implements CarController {

    private static Logger LOGGER = Logger.getLogger(CarControllerImpl.class);
    // @Inject
    CarManager manager;
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

    @PostMapping("/searchCar")
    public ModelAndView searchCar(@Valid SearchCarForm searchCarForm, BindingResult bindingResult) throws Exception {
        LOGGER.info("getting here: " + searchCarForm);
        String registration = searchCarForm.getRegistration().toUpperCase();
        ModelAndView mv = checkAndAddConnectedDetails("operations/operations");
        String token = helper.getConnectedToken();
        if (bindingResult.hasErrors()) {
            return mv;
        }


        Car car = carManager.getByRegistration(helper.getConnectedToken(), registration);
        LOGGER.info("car found: " + car);
        if (car == null) {
            mv.addObject("message", "registration not found in the system");
            mv.addObject("registration", registration);
            mv.addObject("carForm", new CarForm());
            mv.addObject("models", carModelManager.getAll(token));
            return mv;
        }
        mv = checkAndAddConnectedDetails("cars/cars_details");
        LOGGER.info("car: " + car);
        List employees = employeeManager.getAll(token);
        List clients = clientManager.getAll(token);
        List cars = employeeManager.getAll(token);
        LOGGER.info("cars: " + cars.size());
        LOGGER.info("clients: " + clients.size());
        LOGGER.info("employees: " + employees.size());
        LOGGER.info("car details: " + car);
        Client test = (Client) clients.get(1);
        mv.addObject("test", test);
        LOGGER.info("test: " + test);
        ClientList clientList = new ClientList(clientManager.getAll(helper.getConnectedToken()));
        mv.addObject("clientList", clientList);
        mv.addObject("employees", employees);
        mv.addObject("clients", clients);
        mv.addObject("cars", cars);
        mv.addObject("searchCarForm", car);
        mv.addObject("showForm", 1);
        mv.addObject("bills", car.getBills());
        LOGGER.info("bills for car: " + car.getBills().size());
        mv.addObject("currentClient", car.getClient().getId());
        mv.addObject("car", car);

        return mv;


    }


    @GetMapping("")
    public ModelAndView cars() throws Exception {
        LOGGER.info("retrieving cars");
        ModelAndView mv = checkAndAddConnectedDetails("cars/cars");
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
        ModelAndView mv = checkAndAddConnectedDetails("cars/cars_details");
        LOGGER.info("car is null");
        Car car = (Car) manager.getById(helper.getConnectedToken(), id);
        LOGGER.info("car: " + car);
        List employees = employeeManager.getAll(token);
        List clients = clientManager.getAll(token);
        List cars = employeeManager.getAll(token);
        LOGGER.info("cars: " + cars.size());
        LOGGER.info("clients: " + clients.size());
        LOGGER.info("employees: " + employees.size());
        LOGGER.info("car details: " + car);
        ClientList clientList = new ClientList(clientManager.getAll(helper.getConnectedToken()));
        mv.addObject("clientList", clientList);
        mv.addObject("employees", employees);
        mv.addObject("clients", clients);
        mv.addObject("cars", cars);
        mv.addObject("searchCarForm", car);
        mv.addObject("showForm", 1);
        mv.addObject("car", car);
        mv.addObject("bills", car.getBills());
        mv.addObject("currentClient", car.getClient().getId());
        Client test = clientList.getList().get(1);
        mv.addObject("test", test);
        LOGGER.info("test: " + test);
        LOGGER.info("bills for car: " + car.getBills().size());
        return mv;
    }


    @PostMapping(value = "/update/{id}")
    @ResponseBody
    public ModelAndView update(@Valid SearchCarForm searchCarForm, BindingResult bindingResult) throws Exception {
        LOGGER.info("trying to update member with id " + searchCarForm.getId());
        String token = helper.getConnectedToken();
        ModelAndView mv = checkAndAddConnectedDetails("cars/cars_details");

        LOGGER.info("carform: " + searchCarForm);
        if (searchCarForm == null) searchCarForm = new SearchCarForm();
        mv.addObject("carForm", searchCarForm);

        if (bindingResult.hasErrors()) {
            LOGGER.error("binding has errors");
            LOGGER.error("error: " + bindingResult.getFieldError());
            Car car = (Car) manager.getById(token, searchCarForm.getId());
            List<Employee> employees = employeeManager.getAll(token);
            List<Client> clients = clientManager.getAll(token);
            List<Car> cars = employeeManager.getAll(token);
            mv.addObject("employees", employees);
            mv.addObject("clients", clients);
            Client test = clients.get(1);
            mv.addObject("test", test);
            LOGGER.info("test: " + test);
            mv.addObject("cars", cars);
            mv.addObject("car", car);
            mv.addObject("carForm", searchCarForm);
            mv.addObject("showForm", 0);
            mv.addObject("currentClient", car.getClient().getId());
            return mv;
        }
        LOGGER.info("carrying on");
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
        LOGGER.info("trying to create new Car ");
        String token = helper.getConnectedToken();

        ModelAndView mv = checkAndAddConnectedDetails("operations/operations");
        if (bindingResult.hasErrors()) {
            mv.addObject("message", "registration not found in the system");
            mv.addObject("carForm", new CarForm());
            mv.addObject("models", carModelManager.getAll(token));
            return mv;
        }


        LOGGER.info("carForm received: " + carForm);

        int id = carManager.addNewCar(token, carForm);
        if (id != 0) carById(id);


        mv = checkAndAddConnectedDetails("cars/cars_details");
        LOGGER.info("car is null");
        Car car = (Car) manager.getById(helper.getConnectedToken(), id);
        LOGGER.info("car: " + car);
        List employees = employeeManager.getAll(token);
        List clients = clientManager.getAll(token);
        List cars = employeeManager.getAll(token);
        LOGGER.info("cars: " + cars.size());
        LOGGER.info("clients: " + clients.size());
        LOGGER.info("employees: " + employees.size());
        LOGGER.info("car details: " + car);
        ClientList clientList = new ClientList(clientManager.getAll(helper.getConnectedToken()));
        mv.addObject("clientList", clientList);
        mv.addObject("employees", employees);
        mv.addObject("clients", clients);
        mv.addObject("cars", cars);
        mv.addObject("searchCarForm", car);
        mv.addObject("showForm", 1);
        mv.addObject("car", car);
        mv.addObject("bills", car.getBills());
        mv.addObject("currentClient", car.getClient().getId());
        Client test = clientList.getList().get(1);
        mv.addObject("test", test);
        LOGGER.info("test: " + test);
        LOGGER.info("bills for car: " + car.getBills().size());
        return mv;
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
        List<Car> cars = employeeManager.getAll(token);
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
        return cars();
    }

    private CarDTO convertFormIntoDto(SearchCarForm searchCarForm) {
        LOGGER.info("TODO");
        return null;
    }


}

