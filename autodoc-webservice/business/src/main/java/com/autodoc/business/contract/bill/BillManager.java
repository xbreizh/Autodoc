package com.autodoc.business.contract.bill;

import com.autodoc.model.models.bill.Bill;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BillManager {


    boolean save(Bill bill);

    List<Bill> getAll();


}
