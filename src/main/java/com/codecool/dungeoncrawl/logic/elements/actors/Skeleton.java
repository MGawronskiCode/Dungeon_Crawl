package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Enemy {
  public Skeleton(Cell cell) {
    super(cell);
    this.health = 30;
    this.attack = 5;
    this.defence = 0;
  }

  @Override
  public String getTileName() {
    return "skeleton";
  }

  @Override
  public void makeMove(Player player) {
    if (isPlayerNextTo()) {
      attack(player);
    } else {
      randomMove();
    }
  }
}
