package com.autodoc.model;

import com.autodoc.model.models.pieces.Piece;
import com.autodoc.model.models.pieces.PieceType;
import org.apache.log4j.BasicConfigurator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PieceTest {


    private Piece piece;
    private PieceType pieceType;

    @BeforeEach
    void init() {
        BasicConfigurator.configure();
        pieceType = PieceType.builder().name("typo").build();
        System.out.println(pieceType);
        piece = Piece.builder().name("pissou").brand("brandon").pieceType(pieceType).build();
    }

    @Test
    void testToString() {

        assertEquals("Piece{id=0, provider=0, pieceType=0, name='PISSOU', brand='BRANDON', buyingPrice=0.0, sellPrice=0.0, quantity=0}", piece.toString());

    }

}