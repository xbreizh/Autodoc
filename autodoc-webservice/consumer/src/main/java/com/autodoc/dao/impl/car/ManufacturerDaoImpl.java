package com.autodoc.dao.impl.car;

import com.autodoc.dao.contract.car.ManufacturerDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.car.Manufacturer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@SuppressWarnings("unchecked")
public class ManufacturerDaoImpl extends AbstractHibernateDao implements ManufacturerDao {
    private static final Logger LOGGER = Logger.getLogger(ManufacturerDaoImpl.class);
    private Class<?> cl = Manufacturer.class;

    public Class<?> getClazz() {
        return cl;
    }

    @Override
    public Manufacturer getByName(String name) {
        LOGGER.info("get manufacturer by name: " + name);
        TypedQuery<Manufacturer> query = getCurrentSession().createQuery("From Manufacturer where name= :name");
        query.setParameter("name", name.toUpperCase());
        List<Manufacturer> result = query.getResultList();
        if (!result.isEmpty()) {
            LOGGER.info("result: " + result.get(0).toString());
            return result.get(0);
        }
        LOGGER.info("result is empty");
        return null;
    }


    public Map<String, SearchType> getSearchField() {

        return Manufacturer.getSearchField();
    }

}
