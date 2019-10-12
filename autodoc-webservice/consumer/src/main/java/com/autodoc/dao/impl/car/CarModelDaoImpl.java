package com.autodoc.dao.impl.car;

import com.autodoc.dao.contract.car.CarModelDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
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
public class CarModelDaoImpl<T> extends AbstractHibernateDao implements CarModelDao {
    private Logger logger = Logger.getLogger(CarModelDaoImpl.class);

    public CarModelDaoImpl() {
        this.setClazz(CarModel.class);
    }


    @Override
    public CarModel findByName(String name) {
        Query query = getCurrentSession().createQuery("From CarModel where name= :name");
        query.setParameter("name", name);
        System.out.println("in dao");
        List<CarModel> result = (List<CarModel>) query.getResultList();
        if(!result.isEmpty())return result.get(0);
        System.out.println("here");
        return null;
    }


}
