package com.autodoc.dao.filler;

import com.autodoc.dao.contract.bill.BillDao;
import com.autodoc.dao.contract.car.CarDao;
import com.autodoc.dao.contract.car.CarModelDao;
import com.autodoc.dao.contract.car.ManufacturerDao;
import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.dao.contract.person.client.ClientDao;
import com.autodoc.dao.contract.person.employee.EmployeeDao;
import com.autodoc.dao.contract.person.provider.ProviderDao;
import com.autodoc.dao.contract.pieces.PieceDao;
import com.autodoc.dao.contract.pieces.PieceTypeDao;
import com.autodoc.dao.contract.tasks.TaskDao;
import lombok.Builder;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.util.List;

@Transactional
@Named
@Builder
public class Remover {

    static final Logger LOGGER = Logger.getLogger(Remover.class);

    private ManufacturerDao manufacturerDao;
    private CarModelDao carModelDao;
    private EmployeeDao employeeDao;
    private ClientDao clientDao;
    private CarDao carDao;
    //  private CountryDao countryDao;
    private ProviderDao providerDao;
    private PieceDao pieceDao;
    private PieceTypeDao pieceTypeDao;
    private TaskDao taskDao;
    //  private AddressDao addressDao;
    private BillDao billDao;


    public void cleanup() {
        IGenericDao[] daos = {billDao, pieceDao, pieceTypeDao, taskDao, manufacturerDao, carDao, providerDao, clientDao, employeeDao};

        for (IGenericDao dao : daos) {
            LOGGER.info("removing: " + dao.getClass());
            List<?> list = dao.getAll();
            if (!list.isEmpty()) {
                for (Object obj : list) {
                    dao.delete(obj);
                }
            }
        }
    }


}

