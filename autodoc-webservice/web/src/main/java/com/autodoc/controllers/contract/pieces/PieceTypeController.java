package com.autodoc.controllers.contract.pieces;

import org.springframework.http.ResponseEntity;

public interface PieceTypeController {


    ResponseEntity getByName(String name);


}
