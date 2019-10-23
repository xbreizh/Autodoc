package com.autodoc.business.impl.pieces;

import com.autodoc.business.contract.pieces.CategoryManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.pieces.CategoryDaoImpl;
import com.autodoc.model.models.pieces.Category;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class CategoryManagerImpl<T, D> extends AbstractGenericManager implements CategoryManager {
    private CategoryDaoImpl<Category> categoryDao;
    private Logger logger = Logger.getLogger(CategoryManagerImpl.class);
    private ModelMapper mapper;

    public CategoryManagerImpl(CategoryDaoImpl<Category> categoryDao) {
        super(categoryDao);
        this.mapper = new ModelMapper();
        this.categoryDao = categoryDao;
    }


    @Override
    public Object entityToDto(Object entity) {
        return null;
    }

    @Override
    public Object dtoToEntity(Object entity) {
        return null;
    }


}
