package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.elements.actors.Actor;
import lombok.Getter;
import lombok.Setter;

public class Cell implements Drawable {
    private final GameMap gameMap;
    @Getter
    private final int x;
    @Getter
    private final int y;
    @Setter
    @Getter
    private CellType type;
    @Setter
    @Getter
    private Actor actor;
//    @Setter
//    @Getter
//    private Item item;

    Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public Cell getNeighbor(int dx, int dy) {
        return gameMap.getCell(x + dx, y + dy);
    }

    @Override
    public String getTileName() {
        return type.getTileName();
    }

}
