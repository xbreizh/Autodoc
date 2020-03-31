package com.autodoc.controllers.impl.pieces;


import com.autodoc.business.contract.IGenericManager;
import com.autodoc.business.contract.pieces.PieceTypeManager;
import com.autodoc.controllers.contract.pieces.PieceTypeController;
import com.autodoc.controllers.impl.GlobalControllerImpl;
import com.autodoc.model.dtos.pieces.PieceTypeDTO;
import com.autodoc.model.models.pieces.PieceType;
import lombok.Builder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pieceTypes")
@Builder
public class PieceTypeControllerImpl extends GlobalControllerImpl<PieceType, PieceTypeDTO> implements PieceTypeController {
    private final static Logger LOGGER = Logger.getLogger(PieceTypeControllerImpl.class);
    private PieceTypeManager manager;


    public IGenericManager getManager() {
        return manager;
    }

}
