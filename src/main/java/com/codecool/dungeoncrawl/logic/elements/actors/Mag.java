package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Mag extends Enemy {
  public Mag(Cell cell) {
    super(cell);
    this.health = 50;
    this.attack = 20;
    this.defence = 0;
  }

  @Override
  public String getTileName() {
    return "mag";
  }

  @Override
  public void makeMove(Player player) {
    if (isPlayerNextTo() || isPlayerNear()) {
      attack(player);
    } else {
      randomMove();
    }
  }
}
