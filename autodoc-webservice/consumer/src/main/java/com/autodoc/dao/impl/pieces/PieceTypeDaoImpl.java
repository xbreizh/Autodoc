package com.autodoc.dao.impl.pieces;

import com.autodoc.dao.contract.pieces.PieceTypeDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.pieces.PieceType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Map;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@SuppressWarnings("unchecked")
public class PieceTypeDaoImpl<T> extends AbstractHibernateDao implements PieceTypeDao {
    private static final Logger LOGGER = Logger.getLogger(PieceTypeDaoImpl.class);


    private Class<?> cl = PieceType.class;

    public Class<?> getClazz() {
        return cl;
    }

    public Map<String, SearchType> getSearchField() {

        return PieceType.getSearchField();
    }


    @Override
    public PieceType getByName(String name) {
        LOGGER.info("get pieceType by name: " + name);
        TypedQuery<PieceType> query = getCurrentSession().createQuery("From PieceType where name= :name");
        query.setParameter("name", name.toUpperCase());
        if (query.getResultList().isEmpty()) return null;
        return query.getResultList().get(0);
    }

}
