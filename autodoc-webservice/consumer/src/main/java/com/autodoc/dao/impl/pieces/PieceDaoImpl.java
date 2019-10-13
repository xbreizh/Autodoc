package com.autodoc.dao.impl.pieces;

import com.autodoc.dao.contract.pieces.PieceDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.models.pieces.Piece;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class PieceDaoImpl<T> extends AbstractHibernateDao implements PieceDao {
    private Logger logger = Logger.getLogger(PieceDaoImpl.class);


    public PieceDaoImpl() {
        logger.debug("creating manuf dao");
        this.setClazz(Piece.class);
    }


}
