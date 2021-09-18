package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;

import java.util.Objects;
import java.util.Random;

public class Demon extends Enemy {
  private final GameMap actualMap;
  private int teleportCountdown = 10;

  public Demon(Cell cell, GameMap actualMap) {
    super(cell);
    this.health = 999;
    this.attack = 20;
    this.defence = 999;
    this.actualMap = actualMap;
  }

  @Override
  public String getTileName() {
    return "demon";
  }

  @Override
  public void makeMove(Player player) {
    if (isPlayerNextTo()) {
      attack(player);
    } else if (teleportCountdown == 0) {
      teleport();
      teleportCountdown = 10;
    } else {
      moveToPlayer(player);

    }
    teleportCountdown--;
  }

  private void teleport() {
    actualMap.getCell(this.cell.getX(), this.cell.getY()).setActor(null);
    this.cell = randomValidCell();
  }

  private Cell randomValidCell() {
    Cell cellToReturn = null;
    int actualMapWidth = actualMap.getWidth();
    int actualMapHeight = actualMap.getHeight();
    Random random = new Random();
    do {
      int randomX = random.nextInt(actualMapWidth);
      int randomY = random.nextInt(actualMapHeight);
      cellToReturn = new Cell(actualMap, randomX, randomY, CellType.EMPTY);

    } while (!cellValid(Objects.requireNonNull(cellToReturn)));

    return cellToReturn;
  }

  private boolean cellValid(Cell cellToReturn) {
    return cellToReturn.getActor() == null;
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

  @Override
  protected boolean isValidMove(Cell nextCell, CellType nextCellType) {
    return nextCell.getActor() == null;
  }

}
