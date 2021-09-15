package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

import java.util.Random;

public class Hauberk extends Item {

    public int defence;

    public Hauberk(Cell cell) {
        super(cell, "hauberk");
        this.defence = new Random().nextInt(5)+5;
    }

    @Override
    public String getTileName() {
        return "hauberk";
    }

    @Override
    public String toString() {
        return "Hauberk +" + defence;
    }

    public int getDefence() {
        return defence;
    }
}
