package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        boolean isMovementOk = isMovementOk(nextCell);
        if (isMovementOk) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    public boolean isMovementOk(Cell nextCell) {
        String monsterInCell = null;
        if (nextCell.getActor() != null) {
            monsterInCell = nextCell.getActor().getTileName();
        }
        String tileInCell = nextCell.getTileName();
        return !(tileInCell.equals("wall") || "skeleton".equals(monsterInCell));
    }

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
