package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Troll extends Enemy {
    public Troll(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "troll";
    }
}
