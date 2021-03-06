package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.elements.Door;
import com.codecool.dungeoncrawl.logic.elements.actors.Actor;
import com.codecool.dungeoncrawl.logic.items.Item;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class Cell implements Drawable, Serializable {
  private transient final GameMap gameMap;
  @Getter
  private final int x;
  @Getter
  private final int y;
  @Setter
  @Getter
  protected boolean isVisible;
  @Setter
  @Getter
  private CellType type;
  @Setter
  @Getter
  private Actor actor;
  private Door door;
  @Setter
  @Getter
  private Item item;

  public Cell(GameMap gameMap, int x, int y, CellType type) {
    this.gameMap = gameMap;
    this.x = x;
    this.y = y;
    this.type = type;
    this.isVisible = false;
  }

  public Cell getNeighbor(int dx, int dy) {
    try {
      return gameMap.getCell(x + dx, y + dy);
    } catch (Exception ignored) {
    }
    return null;
  }

  @Override
  public String getTileName() {
    return type.getTileName();
  }

  public Door getDoor() {
    return door;
  }

  public void setDoor(Door door) {
    this.door = door;
  }

  public void removeItem() {
    item = null;
    type = CellType.FLOOR;
  }
}