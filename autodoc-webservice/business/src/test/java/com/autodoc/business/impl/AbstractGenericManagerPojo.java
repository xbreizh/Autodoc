package com.autodoc.business.impl;

import com.autodoc.dao.contract.car.CarModelDao;
import lombok.Builder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
@Builder
public class AbstractGenericManagerPojo extends AbstractGenericManager {

    private CarModelDao dao;
}
