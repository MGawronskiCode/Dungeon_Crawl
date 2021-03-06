package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Ghost extends Enemy {

  public Ghost(Cell cell) {
    super(cell);
    this.health = 50;
    this.attack = 1;
    this.defence = 10;
  }

  @Override
  public String getTileName() {
    return "ghost";
  }

  @Override
  protected boolean isValidMove(Cell nextCell, CellType nextCellType) {
    return nextCell.getActor() == null;
  }

  @Override
  protected boolean isValidMove(boolean validMove, Cell nextCell, CellType nextCellType) {
    if ((nextCellType == CellType.EMPTY || nextCellType == CellType.FLOOR || nextCellType == CellType.WALL) && nextCell.getActor() == null) {
      validMove = true;
      cell.setActor(null);
      nextCell.setActor(this);
      cell = nextCell;
    }
    return validMove;
  }
}
