package com.autodoc.dao.impl.car;

import com.autodoc.dao.contract.car.CarModelDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.car.CarModel;
import org.apache.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CarModelDaoImpl<T> extends AbstractHibernateDao implements CarModelDao {
    private static final Logger LOGGER = Logger.getLogger(CarModelDaoImpl.class);
    private Class cl = CarModel.class;

    public Map<String, SearchType> getSearchField() {

        return  CarModel.SEARCH_FIELD;
    }

    public CarModelDaoImpl() {
        this.setClazz(CarModel.class);
    }

    @Override
    public CarModel getByName(String name) {
        LOGGER.info("MEMEMEME");
        Query query = getCurrentSession().createQuery("From CarModel where name= :name", cl);
        query.setParameter("name", name);
        LOGGER.debug("in dao");
        List<CarModel> result = (List<CarModel>) query.getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        }

        LOGGER.debug("here");
        return null;

    }


}
