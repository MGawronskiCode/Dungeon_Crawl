package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Echinops extends Enemy {
    public Echinops(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "echinops";
    }

    @Override
    public void move(int dx, int dy) {
    }
}
