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
import java.util.List;

@Controller
@ControllerAdvice
@RequestMapping("/pieceTypes")
public class PieceTypeControllerImpl extends GlobalController implements PieceTypeController {

    private static Logger LOGGER = Logger.getLogger(PieceTypeControllerImpl.class);
    // @Inject
    PieceTypeManager manager;

    public PieceTypeControllerImpl(LibraryHelper helper, PieceTypeManager manager) {
        super(helper);
        this.manager = manager;
    }


    @GetMapping("")
    public ModelAndView pieceTypes() throws Exception {
        LOGGER.info("retrieving pieceTypes");
        ModelAndView mv = checkAndAddConnectedDetails("pieceTypes/pieceTypes");

        List<PieceTypeDTO> pieceTypes = getPieceTypes();
        LOGGER.info("pieceTypes found: " + pieceTypes.size());

        if (pieceTypes.isEmpty()) {
            return sendError(mv, "no pieceType found");
        }

        mv.addObject("pieceTypes", pieceTypes);
        return mv;

    }

    private List<PieceTypeDTO> getPieceTypes() throws Exception {
        List<PieceTypeDTO> list = (List<PieceTypeDTO>) manager.getAll(helper.getConnectedToken());
        return list;
    }


    @GetMapping(value = "/{id}")
    @ResponseBody
    public ModelAndView pieceTypeById(@PathVariable Integer id) throws Exception {
        LOGGER.info("trying to get member with id " + id);
        ModelAndView mv = checkAndAddConnectedDetails("pieceTypes/pieceTypes_details");
        LOGGER.info("pieceType is null");
        PieceType pieceType = (PieceType) manager.getById(helper.getConnectedToken(), id);
        LOGGER.info("phoneMumber: " + pieceType.getName());
        LOGGER.info("pieceType: " + pieceType);
        mv.addObject("pieceTypeForm", pieceType);
        mv.addObject("showForm", 1);
        mv.addObject("pieceType", pieceType);
        return mv;
    }


    @PostMapping(value = "/update/{id}")
    @ResponseBody
    public ModelAndView update(@Valid PieceTypeForm pieceTypeForm, BindingResult bindingResult) throws Exception {
        LOGGER.info("trying to update member with id " + pieceTypeForm.getId());
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
        return new ModelAndView("redirect:" + "/pieceTypes/" + pieceTypeForm.getId());
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

