package com.autodoc.dao.impl.car;

import com.autodoc.dao.contract.car.ManufacturerDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.car.Manufacturer;
import org.apache.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ManufacturerDaoImpl<T> extends AbstractHibernateDao implements ManufacturerDao {
    private Logger logger = Logger.getLogger(ManufacturerDaoImpl.class);


    public ManufacturerDaoImpl() {
        System.out.println("creating manuf dao");

        this.setClazz(Manufacturer.class);
    }


    @Override
    public Manufacturer getByName(String name) {
        Query query = getCurrentSession().createQuery("From Manufacturer where name= :name");
        query.setParameter("name", name);
        List<Manufacturer> manufacturers =query.getResultList();
        System.out.println("list size: "+manufacturers);
        if(manufacturers.size() > 0)return manufacturers.get(0);
        return null;
    }
}
