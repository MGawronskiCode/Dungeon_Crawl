package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import lombok.Getter;

public class Potion extends Item {
  @Getter
  private final int healthAddingValue;

  public Potion(Cell cell) {
    super(cell, ItemType.POTION);
    healthAddingValue = 30;
  }

  @Override
  public String getTileName() {
    return "potion";
  }

  @Override
  public String toString() {
    return this.getType().toString();
  }
}
