package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Potion extends Item {
    public Potion(Cell cell) {
        super(cell, "potion");
    }

    //    TODO Health (Health +30)

    @Override
    public String getTileName() {
        return "potion";
    }

    @Override
    public String toString() {
        return "Potion";
    }
}
