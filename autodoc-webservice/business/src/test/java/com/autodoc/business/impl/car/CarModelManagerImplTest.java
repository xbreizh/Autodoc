package com.autodoc.business.impl.car;

import com.autodoc.business.contract.car.CarManager;
import com.autodoc.business.contract.car.CarModelManager;
import com.autodoc.business.contract.person.client.ClientManager;
import com.autodoc.dao.contract.car.CarDao;
import com.autodoc.dao.contract.car.CarModelDao;
import com.autodoc.dao.contract.person.client.ClientDao;
import com.autodoc.dao.impl.car.CarModelDaoImpl;
import com.autodoc.model.dtos.car.CarModelDTO;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.car.Manufacturer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
//@Sql(scripts = "classpath:resetDb_scripts/resetDbCar.sql")
@Transactional
class CarModelManagerImplTest {

    // @Inject
    private CarModelManager carModelManager;
    private CarManager carManager;
    //@Inject
    private CarModelDao carModelDao;
    private CarDao carDao1;
    private ClientDao clientDao;
    //@Inject
    private ClientManager clientManager;
    // @Inject
    // private CarModelManager carModelManager;
    private Car car;


    @BeforeEach
    void init() {
        carModelDao = mock(CarModelDaoImpl.class);
        carModelManager = new CarModelManagerImpl(carModelDao);
    }


    @Test
    void getByName() {
        String name = "bob";
        CarModel carModel = new CarModel();
        carModel.setManufacturer(new Manufacturer());
        carModel.setEngine("diesel");
        carModel.setName(name);

        when(carModelDao.getByName(anyString())).thenReturn(carModel);
        // CarModelDTO carModelDTO = (CarModelDTO) carModelManager.entityToDto(carModel);
        assertEquals(name, carModelManager.getByName(name).getName());
    }

    @Test
    void getById() throws Exception {
        int id = 2;
        String name = "bob";
        CarModel carModel = new CarModel();
        carModel.setManufacturer(new Manufacturer());
        carModel.setEngine("diesel");
        carModel.setId(2);
        carModel.setName(name);

        when(carModelDao.getById(anyInt())).thenReturn(carModel);
        // CarModelDTO carModelDTO = (CarModelDTO) carModelManager.entityToDto(carModel);
        CarModelDTO dto = (CarModelDTO) carModelManager.getById(id);
        assertEquals(name, dto.getName());
    }

    @Test
    void getAll() throws Exception {
        List<CarModel> list = new ArrayList<>();
        list.add(new CarModel());
        list.add(new CarModel());
        list.add(new CarModel());

        when(carModelDao.getAll()).thenReturn(list);

        assertEquals(list, carModelDao.getAll());
    }


}
