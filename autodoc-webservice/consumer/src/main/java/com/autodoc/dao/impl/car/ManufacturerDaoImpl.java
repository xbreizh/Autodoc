package com.autodoc.dao.impl.car;

import com.autodoc.dao.contract.car.ManufacturerDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.car.Manufacturer;
import org.apache.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ManufacturerDaoImpl<T> extends AbstractHibernateDao implements ManufacturerDao {
    private static final Logger LOGGER = Logger.getLogger(ManufacturerDaoImpl.class);
    private Class cl = Manufacturer.class;

    public ManufacturerDaoImpl() {
        this.setClazz(Manufacturer.class);
    }

    @Override
    public Manufacturer getByName(String name) {
        LOGGER.info("get manufacturer by name: " + name);
        String req = "From Manufacturer where name= :name";
        Query query = getCurrentSession().createQuery(req, cl);
        query.setParameter("name", name);
        LOGGER.debug("in dao");
        LOGGER.info("request: " + req);
        List<Manufacturer> result = query.getResultList();
        if (!result.isEmpty()) {
            LOGGER.info("result: " + result.get(0).toString());
            return result.get(0);
        }
        LOGGER.info("result is empty");
        LOGGER.debug("here");
        return null;
    }
/*
@Override
    public Manufacturer getByName(String name) {
    LOGGER.info("getting by name");
        Query query = getCurrentSession().createQuery("from "+className+"   where name = :name");
        query.setParameter(name, name);
        if (query.getResultList().isEmpty()) return null;
        return (Manufacturer) query.getResultList().get(0);
    }
*/


    public Map<String, SearchType> getSearchField() {

        return Manufacturer.getSearchField();
    }

}
