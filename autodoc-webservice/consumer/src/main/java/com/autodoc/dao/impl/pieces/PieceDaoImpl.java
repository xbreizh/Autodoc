package com.autodoc.dao.impl.pieces;

import com.autodoc.dao.contract.pieces.PieceDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.models.pieces.Piece;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class PieceDaoImpl<T> extends AbstractHibernateDao implements PieceDao {
    private static final Logger LOGGER = Logger.getLogger(PieceDaoImpl.class);


    public PieceDaoImpl() {
        LOGGER.debug("creating manuf dao");
        this.setClazz(Piece.class);
    }


}
