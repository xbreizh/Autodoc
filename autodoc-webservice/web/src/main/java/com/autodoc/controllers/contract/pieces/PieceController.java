package com.autodoc.controllers.contract.pieces;

import org.springframework.http.ResponseEntity;

public interface PieceController {


    ResponseEntity getByName(String name);


}
