package com.autodoc.dao.impl.bill;

import com.autodoc.dao.contract.bill.BillDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.bill.Bill;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@SuppressWarnings("unchecked")
public class BillDaoImpl<T> extends AbstractHibernateDao implements BillDao {
    private static final Logger LOGGER = Logger.getLogger(BillDaoImpl.class);
    private Class<?> cl = Bill.class;

    public Class<?> getClazz() {
        return cl;
    }


    public Map<String, SearchType> getSearchField() {

        return Bill.getSearchField();
    }
}
