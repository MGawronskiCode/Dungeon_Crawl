package com.codecool.dungeoncrawl.logic.elements;

import com.codecool.dungeoncrawl.logic.Cell;

public class Door extends Element {
  private boolean isOpen;

  public Door(Cell cell) {
    super(cell);
    this.cell.setDoor(this);
    this.isOpen = false;
  }


  @Override
  public String getTileName() {
    if (isOpen) {
      return "openedDoor";
    } else {
      return "closedDoor";
    }
  }

  public void open() { //add key
    this.isOpen = true;
  }

  public boolean isOpen() {
    return isOpen;
  }
}

