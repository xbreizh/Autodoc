package com.autodoc.dao.impl.bill;

import com.autodoc.dao.contract.bill.BillDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.models.car.Car;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class BillDaoImpl<T> extends AbstractHibernateDao implements BillDao {
    private Logger logger = Logger.getLogger(BillDaoImpl.class);

    public BillDaoImpl() {
        this.setClazz(Car.class);
    }


}
