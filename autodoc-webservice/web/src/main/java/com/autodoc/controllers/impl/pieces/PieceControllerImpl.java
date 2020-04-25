package com.autodoc.controllers.impl.pieces;


import com.autodoc.business.contract.IGenericManager;
import com.autodoc.business.contract.pieces.PieceManager;
import com.autodoc.controllers.contract.pieces.PieceController;
import com.autodoc.controllers.impl.GlobalControllerImpl;
import com.autodoc.model.dtos.pieces.PieceDTO;
import com.autodoc.model.models.pieces.Piece;
import lombok.Builder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pieces")
@Builder
public class PieceControllerImpl extends GlobalControllerImpl<Piece, PieceDTO> implements PieceController {
    private final static Logger LOGGER = Logger.getLogger(PieceControllerImpl.class);
    private PieceManager manager;

    public IGenericManager getManager() {
        return manager;
    }

}
