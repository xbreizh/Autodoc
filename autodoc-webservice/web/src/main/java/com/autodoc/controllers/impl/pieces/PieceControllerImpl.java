package com.autodoc.controllers.impl.pieces;


import com.autodoc.business.contract.pieces.PieceManager;
import com.autodoc.controllers.contract.pieces.PieceController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.controllers.impl.GlobalControllerImpl;
import com.autodoc.model.dtos.pieces.PieceDTO;
import com.autodoc.model.models.pieces.Piece;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pieces")
public class PieceControllerImpl extends GlobalControllerImpl<Piece, PieceDTO> implements PieceController {
    private final static Logger LOGGER = Logger.getLogger(PieceControllerImpl.class);
    private PieceManager manager;
    private GsonConverter converter;

    public PieceControllerImpl(PieceManager manager) {
        super(manager);
        converter = new GsonConverter();
        this.manager = manager;
    }


    @Override
    public ResponseEntity getByName(String name) {
        return null;
    }
}
