package com.autodoc.dao.impl.pieces;

import com.autodoc.dao.contract.pieces.PieceDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.pieces.Piece;
import org.apache.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class PieceDaoImpl<T> extends AbstractHibernateDao implements PieceDao {
    private static final Logger LOGGER = Logger.getLogger(PieceDaoImpl.class);


    public PieceDaoImpl() {
        this.setClazz(Piece.class);
    }

    public Map<String, SearchType> getSearchField() {

        return Piece.getSearchField();
    }


    @Override
    public Piece getByName(String name) {
        LOGGER.info("get piece by name: " + name);
        Query query = getCurrentSession().createQuery("From Piece where name= :name");
        query.setParameter("name", name.toUpperCase());
        if (query.getResultList().isEmpty()) return null;
        return (Piece) query.getResultList().get(0);
    }

}
