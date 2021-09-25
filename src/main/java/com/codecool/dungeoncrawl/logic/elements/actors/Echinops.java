package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Echinops extends Enemy {
  public Echinops(Cell cell) {
    super(cell);
    this.health = 150;
    this.attack = 5;
    this.defence = 0;
  }

  @Override
  public String getTileName() {
    return "echinops";
  }

  @Override
  public void makeMove(Player player) {
    if (isPlayerNextTo())
      attack(player);
  }
}
