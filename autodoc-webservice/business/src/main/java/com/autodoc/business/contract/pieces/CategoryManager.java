package com.autodoc.business.contract.pieces;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.models.pieces.Category;

public interface CategoryManager extends IGenericManager {

    String save(Category category);

//    List<Category> getAll();
}
