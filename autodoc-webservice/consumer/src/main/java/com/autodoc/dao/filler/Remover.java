package com.autodoc.dao.filler;

import com.autodoc.dao.contract.bill.BillDao;
import com.autodoc.dao.contract.car.CarDao;
import com.autodoc.dao.contract.car.CarModelDao;
import com.autodoc.dao.contract.car.ManufacturerDao;
import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.dao.contract.person.client.ClientDao;
import com.autodoc.dao.contract.person.employee.EmployeeDao;
import com.autodoc.dao.contract.person.provider.AddressDao;
import com.autodoc.dao.contract.person.provider.CountryDao;
import com.autodoc.dao.contract.person.provider.ProviderDao;
import com.autodoc.dao.contract.pieces.PieceDao;
import com.autodoc.dao.contract.pieces.PieceTypeDao;
import com.autodoc.dao.contract.tasks.TaskDao;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Transactional
@Named
public class Remover {

    static final Logger LOGGER = Logger.getLogger(Remover.class);

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
    private ProviderDao providerDao;

    @Inject
    private PieceDao pieceDao;
    @Inject
    private PieceTypeDao pieceTypeDao;
    @Inject
    private TaskDao taskDao;

    @Inject
    private AddressDao addressDao;
    @Inject
    private BillDao billDao;

    public Remover() {

    }


    public void remover() {
        //, employeeDao, clientDao, carDao, carModelDao, providerDao, addressDao
        IGenericDao[] daos = {billDao, pieceDao, pieceTypeDao, taskDao, countryDao, manufacturerDao, carDao, addressDao, providerDao, clientDao, employeeDao};

        for (int i = 0; i < daos.length; i++) {
            IGenericDao dao = daos[i];
            List list = dao.getAll();
            if (!list.isEmpty()) {
                for (Object obj : list) {
                    billDao.delete(obj);
                }
            }
        }
    }


}

