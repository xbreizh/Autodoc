package com.autodoc.business.contract.pieces;

import com.autodoc.model.models.pieces.Category;

import java.util.List;

public interface CategoryManager {

    String save(Category category);

    List<Category> getAll();
}
