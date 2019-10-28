package com.autodoc.business.impl.pieces;

import com.autodoc.business.contract.pieces.CategoryManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.pieces.CategoryDaoImpl;
import com.autodoc.model.dtos.pieces.CategoryDTO;
import com.autodoc.model.models.pieces.Category;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class CategoryManagerImpl<T, D> extends AbstractGenericManager implements CategoryManager {
    private CategoryDaoImpl<Category> categoryDao;
    private static final Logger LOGGER = Logger.getLogger(CategoryManagerImpl.class);
    private ModelMapper mapper;

    public CategoryManagerImpl(CategoryDaoImpl<Category> categoryDao) {
        super(categoryDao);
        this.mapper = new ModelMapper();
        this.categoryDao = categoryDao;
    }


    @Override
    public CategoryDTO entityToDto(Object entity) {
        CategoryDTO dto = mapper.map(entity, CategoryDTO.class);
        LOGGER.info("converted into dto");
        return dto;
    }

    @Override
    public Category dtoToEntity(Object entity) throws Exception {
        CategoryDTO dto = (CategoryDTO) entity;
        Category category = mapper.map(entity, Category.class);
        checkDataInsert(dto);
        return category;
    }


}
