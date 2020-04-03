package com.autodoc.dao.impl.pieces;

import com.autodoc.dao.contract.pieces.PieceDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.pieces.Piece;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Map;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@SuppressWarnings("unchecked")
public class PieceDaoImpl extends AbstractHibernateDao implements PieceDao {
    private static final Logger LOGGER = Logger.getLogger(PieceDaoImpl.class);
    private Class<?> cl = Piece.class;

    public Class<?> getClazz() {
        return cl;
    }

    public Map<String, SearchType> getSearchField() {

        return Piece.getSearchField();
    }


    @Override
    public Piece getByName(String name) {
        LOGGER.info("get piece by name: " + name);
        TypedQuery<Piece> query = getCurrentSession().createQuery("From Piece where name= :name");
        query.setParameter("name", name.toUpperCase());
        if (query.getResultList().isEmpty()) return null;
        return query.getResultList().get(0);
    }

}
