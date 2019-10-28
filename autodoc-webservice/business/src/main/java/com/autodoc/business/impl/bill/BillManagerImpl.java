package com.autodoc.business.impl.bill;

import com.autodoc.business.contract.bill.BillManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.bill.BillDaoImpl;
import com.autodoc.model.dtos.bill.BillDTO;
import com.autodoc.model.models.bill.Bill;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
public class BillManagerImpl extends AbstractGenericManager implements BillManager {
    private BillDaoImpl billDao;
    private static final Logger LOGGER = Logger.getLogger(BillManagerImpl.class);
    private ModelMapper mapper;

    public BillManagerImpl(BillDaoImpl billDao) {
        super(billDao);
        this.mapper = new ModelMapper();
        this.billDao = billDao;

    }


    @Override
    public String save(Object bill) {
        LOGGER.debug("trying to save a car");
        LOGGER.info("trying to save a like: " + bill);
        billDao.create(bill);
        return "car created";
    }

    @Override
    public List<Bill> getAll() {
        return billDao.getAll();
    }

    @Override
    public BillDTO entityToDto(Object entity) {
        BillDTO dto = mapper.map(entity, BillDTO.class);
        return dto;
    }

    @Override
    public Bill dtoToEntity(Object entity) throws Exception {
        BillDTO dto = (BillDTO) entity;
        Bill bill = mapper.map(entity, Bill.class);
        checkDataInsert(dto);
        return bill;
    }

}
