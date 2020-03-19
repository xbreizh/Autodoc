package com.autodoc.spring.controller.impl;

import com.autodoc.business.contract.CarModelManager;
import com.autodoc.business.contract.PieceManager;
import com.autodoc.business.contract.PieceTypeManager;
import com.autodoc.business.contract.ProviderManager;
import com.autodoc.helper.LibraryHelper;
import com.autodoc.model.dtos.pieces.PieceDTO;
import com.autodoc.model.dtos.pieces.PieceForm;
import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.pieces.Piece;
import com.autodoc.model.models.pieces.PieceType;
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
public class PieceControllerImpl extends GlobalController<Piece, PieceDTO, PieceForm> implements PieceController {

    private static Logger LOGGER = Logger.getLogger(PieceControllerImpl.class);
    private static final String KEY_WORD = "pieces";
    // @Inject
    // PieceManager manager;
    PieceTypeManager pieceTypeManager;
    CarModelManager carModelManager;
    ProviderManager providerManager;

    @Override
    String getKeyWord() {
        return KEY_WORD;
    }

    public PieceControllerImpl(LibraryHelper helper, PieceManager manager, PieceTypeManager pieceTypeManager, CarModelManager carModelManager, ProviderManager providerManager) {
        super(helper);
        this.manager = manager;
        this.providerManager = providerManager;
        this.carModelManager = carModelManager;
        this.pieceTypeManager = pieceTypeManager;
    }


    @GetMapping("")
    public ModelAndView pieces() throws Exception {
        return getList();

    }


    @GetMapping(value = "/{id}")
    @ResponseBody
    public ModelAndView pieceById(@PathVariable Integer id) throws Exception {
        /*LOGGER.info("trying to get piece with id " + id);
        ModelAndView mv = checkAndAddConnectedDetails("pieces/pieces_details");
        LOGGER.info("piece is null");
        Piece piece = (Piece) manager.getById(helper.getConnectedToken(), id);
        LOGGER.info("name: " + piece.getName());
        LOGGER.info("piece: " + piece);
        mv.addObject("pieceForm", piece);
        mv.addObject("showForm", 1);
        mv.addObject("piece", piece);*/

        ModelAndView mv = getById(id);
        List<CarModel> carModels = carModelManager.getAll(helper.getConnectedToken());
        List<PieceType> pieceTypes = pieceTypeManager.getAll(helper.getConnectedToken());
        mv.addObject("carModels", carModels);
        mv.addObject("pieceTypes", pieceTypes);
        return mv;
    }


    @PostMapping(value = "/update/{id}")
    @ResponseBody
    public ModelAndView update(@Valid PieceForm pieceForm, BindingResult bindingResult) throws Exception {

        LOGGER.info("trying to update piece with id " + pieceForm.getId());
        ModelAndView mv = checkAndAddConnectedDetails("pieces/pieces_details");
        mv.addObject("pieceForm", new PieceForm());
        List<CarModel> carModels = carModelManager.getAll(helper.getConnectedToken());
        List<PieceType> pieceTypes = pieceTypeManager.getAll(helper.getConnectedToken());
        mv.addObject("carModels", carModels);
        mv.addObject("pieceTypes", pieceTypes);
        if (bindingResult.hasErrors()) {
            LOGGER.error("binding has errors");
            LOGGER.error(bindingResult.getFieldError().getDefaultMessage());
            LOGGER.info("pieceForm: "+pieceForm);
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
    public ModelAndView delete(@PathVariable Integer id) throws Exception {
        LOGGER.info("trying to delete member with id " + id);
        manager.delete(helper.getConnectedToken(), id);
        return pieces();
    }

    @GetMapping(value = "/new")
    public ModelAndView getCreate() throws Exception {
        LOGGER.info("getting create form");
        ModelAndView mv = checkAndAddConnectedDetails("pieces/pieces_new");
        List<CarModel> carModels = carModelManager.getAll(helper.getConnectedToken());
        List<PieceType> pieceTypes = pieceTypeManager.getAll(helper.getConnectedToken());
        mv.addObject("carModels", carModels);
        mv.addObject("pieceTypes", pieceTypes);
        mv.addObject("pieceForm", new PieceForm());
        mv.addObject("showForm", 1);
        return mv;
    }


    @PostMapping(value = "/new")
    @ResponseBody
    public ModelAndView create(@Valid PieceForm pieceForm, BindingResult bindingResult) throws Exception {
        LOGGER.info("trying to create member ");
        ModelAndView mv = checkAndAddConnectedDetails("pieces/pieces_new");
        LOGGER.info("pieceForm: " + pieceForm);
        mv.addObject("pieceForm", new PieceForm());
        List<CarModel> carModels = carModelManager.getAll(helper.getConnectedToken());
        List<PieceType> pieceTypes = pieceTypeManager.getAll(helper.getConnectedToken());
        mv.addObject("carModels", carModels);
        mv.addObject("pieceTypes", pieceTypes);
        List<PieceType> pieceTypeList = pieceTypeManager.getAll(helper.getConnectedToken());
        LOGGER.info("tasks: " + pieceTypeList);
        mv.addObject("pieceTypeList", pieceTypeList);
        if (bindingResult.hasErrors()) {
            LOGGER.error("binding has errors");
            mv.addObject("pieceForm", pieceForm);
            mv.addObject("showForm", 1);
            return new ModelAndView("redirect:/pieces");
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

