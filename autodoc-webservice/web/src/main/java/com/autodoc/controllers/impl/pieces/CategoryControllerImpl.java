package com.autodoc.controllers.impl.pieces;


import com.autodoc.business.contract.pieces.CategoryManager;
import com.autodoc.controllers.contract.pieces.CategoryController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.controllers.impl.GlobalControllerImpl;
import com.autodoc.model.dtos.pieces.CategoryDTO;
import com.autodoc.model.models.pieces.Category;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryControllerImpl extends GlobalControllerImpl<Category, CategoryDTO> implements CategoryController {
    private final static Logger LOGGER = Logger.getLogger(CategoryControllerImpl.class);
    private CategoryManager manager;
    private GsonConverter converter;

    public CategoryControllerImpl(CategoryManager manager) {
        super(manager);
        converter = new GsonConverter();
        this.manager = manager;
    }


    @Override
    public ResponseEntity getByName(String name) {
        System.out.println("gimme a name");
        return null;
    }
}
