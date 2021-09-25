package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Warrior extends Enemy {
  public Warrior(Cell cell) {
    super(cell);
    this.health = 50;
    this.attack = 30;
    this.defence = 15;
  }

  @Override
  public String getTileName() {
    return "warrior";
  }
}
