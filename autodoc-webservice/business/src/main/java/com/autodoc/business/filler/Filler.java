package com.autodoc.business.filler;

import com.autodoc.dao.contract.car.CarDao;
import com.autodoc.dao.contract.car.CarModelDao;
import com.autodoc.dao.contract.car.ManufacturerDao;
import com.autodoc.dao.contract.person.client.ClientDao;
import com.autodoc.dao.contract.person.employee.EmployeeDao;
import com.autodoc.dao.contract.person.employee.SkillCategoryDao;
import com.autodoc.dao.contract.person.employee.SkillDao;
import com.autodoc.dao.contract.person.provider.CountryDao;
import com.autodoc.dao.contract.person.provider.ProviderDao;
import com.autodoc.dao.impl.car.CarDaoImpl;
import com.autodoc.dao.impl.car.CarModelDaoImpl;
import com.autodoc.dao.impl.car.ManufacturerDaoImpl;
import com.autodoc.dao.impl.person.client.ClientDaoImpl;
import com.autodoc.dao.impl.person.employee.EmployeeDaoImpl;
import com.autodoc.model.enums.FuelType;
import com.autodoc.model.enums.GearBox;
import com.autodoc.model.enums.Role;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.car.Manufacturer;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.model.models.person.employee.Employee;
import com.autodoc.model.models.person.employee.Skill;
import com.autodoc.model.models.person.employee.SkillCategory;
import com.autodoc.model.models.person.provider.Country;
import com.autodoc.model.models.person.provider.Provider;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Component
public class Filler {

    private static final Logger LOGGER = Logger.getLogger(Filler.class);

    @Inject
    private ManufacturerDao manufacturerDao;
    @Inject
    private CarModelDao carModelDao;
    @Inject
    private EmployeeDao employeeDao;
    @Inject
    private ClientDao clientDao;
    @Inject
    private CarDao carDao;
    @Inject
    private CountryDao countryDao;
    @Inject
    private SkillCategoryDao skillCategoryDao;
    @Inject
    private SkillDao skillDao;
    @Inject
    private ProviderDao providerDao;

    public Filler() {

    }

    public void fill() {
        LOGGER.debug("getting here");
        fillEmployee();
        fillManufacturer();
        fillCarModel();
        fillClient();
        fillCar();
        fillCountry();
        fillSkillCategory();
        fillSkill();
        fillProvider();
    }

    private void fillProvider() {
        LOGGER.debug("filling providers");
        Provider provider = new Provider("Paul", "Morigo", "121215", "info@mazda.ie", "MAZDA" );
        providerDao.create(provider);
        Provider provider1 = new Provider("JACQUES", "PLACO", "124542", "info@RENAUDO.ie", "RENAUDO" );
        providerDao.create(provider1);
    }

    private void fillSkill() {
        LOGGER.debug("filling skills");
        Skill skill = new Skill();
        Skill skill1 = new Skill();
        Skill skill2 = new Skill();
        SkillCategory skillCat1 = (SkillCategory) skillCategoryDao.getById(1);
        SkillCategory skillCat2 = (SkillCategory) skillCategoryDao.getById(1);
        skill.setSkillCategory(skillCat1);
        skill.setName("changement de courroie");
        skillDao.create(skill);
        skill1.setSkillCategory(skillCat2);
        skill1.setName("changement de tambours");
        skillDao.create(skill1);
        skill2.setSkillCategory(skillCat1);
        skill2.setName("changement de plaquettes");
        skillDao.create(skill2);
    }


    private void fillSkillCategory() {
        LOGGER.debug("filling skill categories");
        String[] list = {"VIDANGE", "FREIN", "PNEUS", "MAINTENANCE", "CLIMATISATION"};
        for (int i = 0; i < list.length; i++) {
            skillCategoryDao.create(new SkillCategory(list[i]));
        }

    }


    private void fillCountry() {
        LOGGER.debug("filling countries");
        String[] list = {"SPAIN", "IRELAND", "MEXICO", "JAPAN", "NEW-ZEALAND", "BELGIUM"};
        for (int i = 0; i < list.length; i++) {
            countryDao.create(new Country(list[i]));
        }
    }

    private void fillManufacturer() {
        LOGGER.debug("filling manufacturer");
        String[] list = {"AUDI", "BMW", "RENAULT", "OPEL", "NISSAN", "TOYOTA"};
        for (int i = 0; i < list.length; i++) {
            manufacturerDao.create(new Manufacturer(list[i]));
        }
    }


    private void fillCarModel() {
        LOGGER.debug("filling car model");
        Manufacturer man = manufacturerDao.getByName("NISSAN");
        carModelDao.create(new CarModel(man, "QASHQAI", "VISIA DCI", GearBox.AUTOMATIC, "1528", FuelType.DIESEL));
        man = manufacturerDao.getByName("RENAULT");
        carModelDao.create(new CarModel(man, "CLIO", "BEBOP", GearBox.MANUAL, "1528", FuelType.PETROL));
        man = manufacturerDao.getByName("TOYOTA");
        carModelDao.create(new CarModel(man, "AURIS", "T SPIRIT D4D", GearBox.MANUAL, "1998", FuelType.HYBRID));
    }

    private void fillClient() {
        LOGGER.debug("filling client");
        Client client = new Client("LOKII", "MOLO", "03938937837");
        Client client1 = new Client("ROGER", "MOORE", "0584759852");
        clientDao.create(client);
        clientDao.create(client1);
    }

    private void fillCar() {
        LOGGER.debug("filling car");
        CarModel carModel = carModelDao.findByName("AURIS");
        Client client = (Client) clientDao.getAll().get(0);
        Car car = new Car("05D154875", carModel, client);
        carDao.create(car);
    }

    private void fillEmployee() {
        LOGGER.debug("filling employee");
        List<Role> roleList = new ArrayList<>();
        String login = "LMOLO";
        Employee employee = new Employee("LOKII", "MOLO", "03938937837", roleList, new Date(), login, "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6");
        employeeDao.create(employee);
    }


}
