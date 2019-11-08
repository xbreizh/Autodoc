package com.autodoc.business.impl.bill;

import com.autodoc.business.contract.bill.BillManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.contract.bill.BillDao;
import com.autodoc.model.dtos.bill.BillDTO;
import com.autodoc.model.models.bill.Bill;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class BillManagerImpl extends AbstractGenericManager implements BillManager {
    private BillDao billDao;
    private static final Logger LOGGER = Logger.getLogger(BillManagerImpl.class);
    private ModelMapper mapper;

    public BillManagerImpl(BillDao billDao) {
        super(billDao);
        this.mapper = new ModelMapper();
        this.billDao = billDao;

    }


    @Override
    public BillDTO entityToDto(Object entity) {
        System.out.println("convert to dto");
        BillDTO dto = mapper.map(entity, BillDTO.class);
        Bill bill = (Bill) entity;
        dto.setRegistration(((Bill) entity).getCar().getRegistration());
        return dto;
    }

    @Override
    public Bill dtoToEntity(Object entity) throws Exception {
        System.out.println("converts to entity");
        BillDTO dto = (BillDTO) entity;
        Bill bill = mapper.map(dto, Bill.class);
        checkDataInsert(dto);
        return bill;
    }

}
