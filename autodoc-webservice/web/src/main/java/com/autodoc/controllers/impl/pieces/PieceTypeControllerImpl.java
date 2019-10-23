package com.autodoc.controllers.impl.pieces;


import com.autodoc.business.contract.pieces.PieceTypeManager;
import com.autodoc.controllers.contract.pieces.PieceTypeController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.controllers.impl.GlobalControllerImpl;
import com.autodoc.model.dtos.pieces.PieceTypeDTO;
import com.autodoc.model.models.pieces.PieceType;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pieceTypes")
public class PieceTypeControllerImpl extends GlobalControllerImpl<PieceType, PieceTypeDTO> implements PieceTypeController {
    private final static Logger LOGGER = Logger.getLogger(PieceTypeControllerImpl.class);
    private PieceTypeManager manager;
    private GsonConverter converter;

    public PieceTypeControllerImpl(PieceTypeManager manager) {
        super(manager);
        converter = new GsonConverter();
        this.manager = manager;
    }


    @Override
    public ResponseEntity getByName(String name) {
        return null;
    }
}
