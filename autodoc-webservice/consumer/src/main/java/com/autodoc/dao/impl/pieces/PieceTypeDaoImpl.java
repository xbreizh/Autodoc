package com.autodoc.dao.impl.pieces;

import com.autodoc.dao.contract.pieces.PieceTypeDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.models.pieces.PieceType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class PieceTypeDaoImpl<T> extends AbstractHibernateDao implements PieceTypeDao {
    private Logger logger = Logger.getLogger(PieceTypeDaoImpl.class);


    public PieceTypeDaoImpl() {
        System.out.println("creating manuf dao");
        this.setClazz(PieceType.class);
    }


}
