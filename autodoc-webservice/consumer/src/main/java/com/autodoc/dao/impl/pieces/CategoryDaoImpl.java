package com.autodoc.dao.impl.pieces;

import com.autodoc.dao.contract.pieces.CategoryDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.models.pieces.Category;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CategoryDaoImpl<T> extends AbstractHibernateDao implements CategoryDao {
    private Logger logger = Logger.getLogger(CategoryDaoImpl.class);


    public CategoryDaoImpl() {
        logger.debug("creating manuf dao");
        this.setClazz(Category.class);
    }


}
