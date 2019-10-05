package com.autodoc.dao.impl;

import com.autodoc.dao.contract.CarDao;
import com.autodoc.dao.contract.CarModelDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.car.CarModel;
import org.apache.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CarModelDaoImpl<T extends Serializable> extends AbstractHibernateDao implements CarModelDao {
    private Logger logger = Logger.getLogger(CarModelDaoImpl.class);
    public CarModelDaoImpl() {
        this.setClazz(CarModel.class);
    }


}
