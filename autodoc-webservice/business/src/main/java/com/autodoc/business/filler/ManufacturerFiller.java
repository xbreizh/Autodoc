package com.autodoc.business.filler;

import com.autodoc.dao.impl.car.CarModelDaoImpl;
import com.autodoc.dao.impl.car.ManufacturerDaoImpl;
import com.autodoc.dao.impl.person.employee.EmployeeDaoImpl;
import com.autodoc.model.enums.FuelType;
import com.autodoc.model.enums.GearBox;
import com.autodoc.model.enums.Role;
import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.car.Manufacturer;
import com.autodoc.model.models.person.employee.Employee;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Component
public class ManufacturerFiller {

    private ManufacturerDaoImpl<Manufacturer> manufacturerDao;
    private CarModelDaoImpl<CarModel> carModelDao;
    private EmployeeDaoImpl<Employee> employeeDao;

    public ManufacturerFiller(ManufacturerDaoImpl<Manufacturer> manufacturerDao, CarModelDaoImpl<CarModel> carModelDao, EmployeeDaoImpl<Employee> employeeDao) {
        this.manufacturerDao = manufacturerDao;
        this.carModelDao = carModelDao;
        this.employeeDao = employeeDao;
    }

    public void fill(){
        System.out.println("getting here");
        fillManufacturer();
        fillCarModel();
        fillEmployee();
    }

    private void fillManufacturer(){
        System.out.println("filling manufacturer");
        String[] list = {"AUDI", "BMW", "RENAULT", "OPEL", "NISSAN", "TOYOTA"};
        for (int i = 0; i < list.length; i++) {
            manufacturerDao.create(new Manufacturer(list[i]));
        }
    }


    private void fillCarModel(){
        Manufacturer man = manufacturerDao.getByName("NISSAN");
        carModelDao.create(new CarModel(man,"QASHQAI", "VISIA DCI", GearBox.AUTOMATIC, "1528", FuelType.DIESEL ));
        man = manufacturerDao.getByName("RENAULT");
        carModelDao.create(new CarModel(man,"CLIO", "BEBOP", GearBox.MANUAL, "1528", FuelType.PETROL ));
        man = manufacturerDao.getByName("TOYOTA");
        carModelDao.create(new CarModel(man,"AURIS", "T SPIRIT D4D", GearBox.MANUAL, "1998", FuelType.HYBRID ));
    }

    private void fillEmployee(){
        List<Role> roleList = new ArrayList<>();
        Employee employee = new Employee("LOKII", "MOLO", "03938937837", roleList, new Date(),"LMOLO", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6");
        employeeDao.create(employee);
    }


}
