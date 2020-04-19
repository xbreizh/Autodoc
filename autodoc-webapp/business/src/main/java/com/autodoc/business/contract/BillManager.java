package com.autodoc.business.contract;


import com.autodoc.model.dtos.SearchDto;
import com.autodoc.model.models.bill.Bill;

import java.util.List;

public interface BillManager extends GlobalManager {

    List<Bill> getByRegistration(String token, SearchDto searchDto);

    List<String> getStatus(String token);

    List<String> getPaymentType(String token);


}
