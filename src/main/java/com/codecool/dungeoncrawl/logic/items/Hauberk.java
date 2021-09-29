package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import lombok.Getter;

import java.util.Random;

public class Hauberk extends Item {
  @Getter
  public int defence;

  public Hauberk(Cell cell) {
    super(cell, ItemType.HAUBERK);
    this.defence = new Random().nextInt(5) + 5;
  }

  @Override
  public String toString() {
    return this.getType() + " +" + defence;
  }

  @Override
  public String getTileName() {
    return null;
  }
}
