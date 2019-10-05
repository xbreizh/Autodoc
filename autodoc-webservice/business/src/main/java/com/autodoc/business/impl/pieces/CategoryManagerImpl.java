package com.autodoc.business.impl.pieces;

import com.autodoc.business.contract.pieces.CategoryManager;
import com.autodoc.dao.impl.pieces.CategoryDaoImpl;
import com.autodoc.model.models.pieces.Category;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
public class CategoryManagerImpl implements CategoryManager {
    private CategoryDaoImpl<Category> categoryDao;
    private Logger logger = Logger.getLogger(CategoryManagerImpl.class);


    public CategoryManagerImpl(CategoryDaoImpl<Category> categoryDao) {
        this.categoryDao = categoryDao;

    }


    @Override
    public String save(Category category) {
        categoryDao.create(category);
        return "category added";
    }

    @Override
    public List<Category> getAll() {
        return null;
    }
}
