package com.autodoc.spring.controller.impl;

import com.autodoc.business.contract.PieceManager;
import com.autodoc.helper.LibraryHelper;
import com.autodoc.model.dtos.pieces.PieceDTO;
import com.autodoc.model.dtos.pieces.PieceForm;
import com.autodoc.model.models.pieces.Piece;
import com.autodoc.spring.controller.contract.PieceController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@ControllerAdvice
@RequestMapping("/pieces")
public class PieceControllerImpl extends GlobalController implements PieceController {

    private static Logger LOGGER = Logger.getLogger(PieceControllerImpl.class);
    // @Inject
    PieceManager manager;

    public PieceControllerImpl(LibraryHelper helper, PieceManager manager) {
        super(helper);
        this.manager = manager;
    }


    @GetMapping("")
    public ModelAndView pieces() {
        LOGGER.info("retrieving pieces");
        ModelAndView mv = checkAndAddConnectedDetails("pieces");

        List<PieceDTO> pieces = getPieces();
        LOGGER.info("pieces found: " + pieces.size());

        if (pieces.isEmpty()) {
            return sendError(mv, "no piece found");
        }

        mv.addObject("pieces", pieces);
        return mv;

    }

    private List<PieceDTO> getPieces() {
        List<PieceDTO> list = (List<PieceDTO>) manager.getAll(helper.getConnectedToken());
        return list;
    }


    @GetMapping(value = "/{id}")
    @ResponseBody
    public ModelAndView pieceById(@PathVariable Integer id) throws Exception {
        LOGGER.info("trying to get member with id " + id);
        ModelAndView mv = checkAndAddConnectedDetails("pieces_details");
        System.out.println("piece is null");
        Piece piece = (Piece) manager.getById(helper.getConnectedToken(), id);
        LOGGER.info("phoneMumber: " + piece.getName());
        LOGGER.info("piece: " + piece);
        mv.addObject("pieceForm", piece);
        mv.addObject("showForm", 1);
        mv.addObject("piece", piece);
        return mv;
    }


    @PostMapping(value = "/update/{id}")
    @ResponseBody
    public ModelAndView update(@Valid PieceForm pieceForm, BindingResult bindingResult) throws Exception {
        LOGGER.info("trying to update member with id " + pieceForm.getId());
        ModelAndView mv = checkAndAddConnectedDetails("pieces_details");
        mv.addObject("pieceForm", new PieceForm());
        if (bindingResult.hasErrors()) {
            LOGGER.error("binding has errors");
            Piece piece = (Piece) manager.getById(helper.getConnectedToken(), pieceForm.getId());
            mv.addObject("piece", piece);
            mv.addObject("pieceForm", pieceForm);
            mv.addObject("showForm", 0);
            return mv;
        }
        LOGGER.info("carrying on");
        LOGGER.info("piece retrieved: " + pieceForm);
        manager.update(helper.getConnectedToken(), pieceForm);
        return new ModelAndView("redirect:" + "/pieces/" + pieceForm.getId());
    }

    @GetMapping(value = "/delete/{id}")
    @ResponseBody
    public ModelAndView delete(@PathVariable Integer id) {
        LOGGER.info("trying to delete member with id " + id);
        manager.delete(helper.getConnectedToken(), id);
        return pieces();
    }

    @GetMapping(value = "/new")
    public ModelAndView getCreate() {
        LOGGER.info("getting create form");
        ModelAndView mv = checkAndAddConnectedDetails("pieces_new");
        mv.addObject("pieceForm", new PieceForm());
        mv.addObject("showForm", 1);
        return mv;
    }


    @PostMapping(value = "/new")
    @ResponseBody
    public ModelAndView create(@Valid PieceForm pieceForm, BindingResult bindingResult) {
        LOGGER.info("trying to create member ");
        ModelAndView mv = checkAndAddConnectedDetails("pieces_new");
        LOGGER.info("empl: " + pieceForm);
        mv.addObject("pieceForm", new PieceForm());
        if (bindingResult.hasErrors()) {
            LOGGER.error("binding has errors");
            mv.addObject("pieceForm", pieceForm);
            mv.addObject("showForm", 1);
            return mv;
        }
        LOGGER.info("piece retrieved: " + pieceForm);
        manager.add(helper.getConnectedToken(), pieceForm);
        return pieces();
    }

    private PieceDTO convertFormIntoDto(PieceForm pieceForm) {
        LOGGER.info("TODO");
        return null;
    }


}

