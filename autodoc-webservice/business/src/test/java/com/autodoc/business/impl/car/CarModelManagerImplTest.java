package com.autodoc.business.impl.car;

/*

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
*/
