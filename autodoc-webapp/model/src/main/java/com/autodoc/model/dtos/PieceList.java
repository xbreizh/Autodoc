package com.autodoc.model.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class PieceList {

    private List<Integer> list = new ArrayList<>();

    public PieceList(List<Integer> list) {
        this.list = list;
    }

    public PieceList() {
    }

    public void addPiece(Integer pieceId) {
        list.add(pieceId);
    }


    @Override
    public String toString() {
        return "PieceList{" +
                "list=" + list +
                '}';
    }
}