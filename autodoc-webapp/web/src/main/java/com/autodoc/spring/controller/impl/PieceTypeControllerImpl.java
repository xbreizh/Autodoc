package com.autodoc.spring.controller.impl;

import com.autodoc.business.contract.PieceTypeManager;
import com.autodoc.helper.LibraryHelper;
import com.autodoc.model.dtos.pieces.PieceTypeDTO;
import com.autodoc.model.dtos.pieces.PieceTypeForm;
import com.autodoc.model.models.pieces.PieceType;
import com.autodoc.spring.controller.contract.PieceTypeController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@ControllerAdvice
@RequestMapping("/pieceTypes")
public class PieceTypeControllerImpl extends GlobalController<PieceType, PieceTypeDTO, PieceTypeForm> implements PieceTypeController {

    private static Logger LOGGER = Logger.getLogger(PieceTypeControllerImpl.class);
    // @Inject
    // PieceTypeManager manager;
    private static final String KEY_WORD = "pieceTypes";

    public PieceTypeControllerImpl(LibraryHelper helper, PieceTypeManager manager) {
        super(helper);
        this.manager = manager;
    }

    @Override
    String getKeyWord() {
        return KEY_WORD;
    }

    @GetMapping("")
    public ModelAndView pieceTypes() throws Exception {
        return getList();

    }


    @GetMapping(value = "/{id}")
    @ResponseBody
    public ModelAndView pieceTypeById(@PathVariable Integer id) throws Exception {
        return getById(id);
    }


    @PostMapping(value = "/update/{id}")
    @ResponseBody
    public ModelAndView update(@Valid PieceTypeForm form, BindingResult bindingResult) throws Exception {

        if (form == null) form = new PieceTypeForm();
        ModelAndView mv = updateObject(form, form.getId(), bindingResult);


       /* LOGGER.info("trying to update member with id " + pieceTypeForm.getId());
        ModelAndView mv = checkAndAddConnectedDetails("pieceTypes/pieceTypes_details");
        mv.addObject("pieceTypeForm", new PieceTypeForm());
        if (bindingResult.hasErrors()) {
            LOGGER.error("binding has errors");
            PieceType pieceType = (PieceType) manager.getById(helper.getConnectedToken(), pieceTypeForm.getId());
            mv.addObject("pieceType", pieceType);
            mv.addObject("pieceTypeForm", pieceTypeForm);
            mv.addObject("showForm", 0);
            return mv;
        }
        LOGGER.info("carrying on");
        LOGGER.info("pieceType retrieved: " + pieceTypeForm);
        manager.update(helper.getConnectedToken(), pieceTypeForm);
        return new ModelAndView("redirect:" + "/pieceTypes/" + pieceTypeForm.getId());*/
        return mv;
    }

    @GetMapping(value = "/delete/{id}")
    @ResponseBody
    public ModelAndView delete(@PathVariable Integer id) throws Exception {
        LOGGER.info("trying to delete pieceType with id " + id);
        manager.delete(helper.getConnectedToken(), id);
        LOGGER.info("pieceType must have been deleted");
        return pieceTypes();
    }

    @GetMapping(value = "/new")
    public ModelAndView getCreate() {
        LOGGER.info("getting create form");
        ModelAndView mv = checkAndAddConnectedDetails("pieceTypes/pieceTypes_new");
        mv.addObject("pieceTypeForm", new PieceTypeForm());
        mv.addObject("showForm", 1);
        return mv;
    }


    @PostMapping(value = "/new")
    @ResponseBody
    public ModelAndView create(@Valid PieceTypeForm pieceTypeForm, BindingResult bindingResult) throws Exception {
        LOGGER.info("trying to create member ");
        ModelAndView mv = checkAndAddConnectedDetails("pieceTypes/pieceTypes_new");
        LOGGER.info("empl: " + pieceTypeForm);
        mv.addObject("pieceTypeForm", new PieceTypeForm());
        if (bindingResult.hasErrors()) {
            LOGGER.error("binding has errors");
            mv.addObject("pieceTypeForm", pieceTypeForm);
            mv.addObject("showForm", 1);
            return mv;
        }
        LOGGER.info("pieceType retrieved: " + pieceTypeForm);
        manager.add(helper.getConnectedToken(), pieceTypeForm);
        return new ModelAndView("redirect:/pieceTypes");
    }



}

