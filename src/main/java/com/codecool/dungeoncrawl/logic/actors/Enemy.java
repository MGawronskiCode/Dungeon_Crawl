package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.elements.actors.Actor;

public class Enemy extends Actor {

    public Enemy(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "enemy";
    }
}
