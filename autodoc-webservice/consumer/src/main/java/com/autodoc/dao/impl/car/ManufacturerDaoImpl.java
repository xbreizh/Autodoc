package com.autodoc.dao.impl.car;

import com.autodoc.dao.contract.car.ManufacturerDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.models.car.Manufacturer;
import org.apache.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ManufacturerDaoImpl extends AbstractHibernateDao implements ManufacturerDao {
    private Logger logger = Logger.getLogger(ManufacturerDaoImpl.class);
    private Class cl = Manufacturer.class;

    public ManufacturerDaoImpl() {
        logger.debug("creating manuf dao");

        this.setClazz(Manufacturer.class);
    }


    @Override
    public Manufacturer getByName(String name) {
        Query query = getCurrentSession().createQuery("From Manufacturer where name= :name");
        query.setParameter("name", name);
        logger.debug("in dao");
        List<Manufacturer> result = query.getResultList();
        if (!result.isEmpty()) {
            System.out.println("result: " + result.get(0).toString());
            return (Manufacturer) result.get(0);
        }

        logger.debug("here");
        return null;
    }


}
