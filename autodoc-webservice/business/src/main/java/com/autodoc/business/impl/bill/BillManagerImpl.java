package com.autodoc.business.impl.bill;

import com.autodoc.business.contract.bill.BillManager;
import com.autodoc.dao.impl.bill.BillDaoImpl;
import com.autodoc.model.models.bill.Bill;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
public class BillManagerImpl implements BillManager {
    private BillDaoImpl<Bill> billDao;
    private Logger logger = Logger.getLogger(BillManagerImpl.class);


    public BillManagerImpl(BillDaoImpl<Bill> billDao) {
        this.billDao = billDao;

    }


    @Override
    public boolean save(Bill bill) {
        logger.debug("trying to save a car");
        logger.info("trying to save a like: " + bill);
        //car.setRegistration("morning");
        billDao.create(bill);
        return true;
    }

    @Override
    public List<Bill> getAll() {
        return billDao.findAll();
    }

}
