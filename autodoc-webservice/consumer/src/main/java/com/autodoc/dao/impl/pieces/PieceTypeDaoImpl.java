package com.autodoc.dao.impl.pieces;

import com.autodoc.dao.contract.pieces.PieceTypeDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.models.pieces.PieceType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class PieceTypeDaoImpl<T> extends AbstractHibernateDao implements PieceTypeDao {
    private static final Logger LOGGER = Logger.getLogger(PieceTypeDaoImpl.class);


    public PieceTypeDaoImpl() {
        LOGGER.debug("creating manuf dao");
        this.setClazz(PieceType.class);
    }


}
