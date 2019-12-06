package com.autodoc.dao.impl.pieces;

import com.autodoc.dao.contract.pieces.PieceTypeDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.pieces.PieceType;
import org.apache.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class PieceTypeDaoImpl<T> extends AbstractHibernateDao implements PieceTypeDao {
    private static final Logger LOGGER = Logger.getLogger(PieceTypeDaoImpl.class);


    public PieceTypeDaoImpl() {
        LOGGER.debug("creating manuf dao");
        this.setClazz(PieceType.class);
    }

    public Map<String, SearchType> getSearchField() {

        return PieceType.SEARCH_FIELD;
    }


    @Override
    public PieceType getByName(String name) {
        System.out.println("get pieceType by name: " + name);
        Query query = getCurrentSession().createQuery("From PieceType where name= :name");
        query.setParameter("name", name.toUpperCase());
        if (query.getResultList().isEmpty()) return null;
        return (PieceType) query.getResultList().get(0);
    }

}
