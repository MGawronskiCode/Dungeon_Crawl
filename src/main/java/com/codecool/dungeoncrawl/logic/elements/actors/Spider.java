package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Spider extends Enemy {
  public Spider(Cell cell) {
    super(cell);
    this.health = 30;
    this.attack = 20;
    this.defence = 5;
  }

  @Override
  public String getTileName() {
    return "spider";
  }

  @Override
  public void makeMove(Player player) {
    if (isPlayerNextTo()) {
      attack(player);
      attack = attack + 5;
    } else if (isPlayerNear()) {
      attack = 20;
      moveToPlayer(player);
    } else {
      attack = 20;
      randomMove();
    }
  }

  @Override
  public void attack(Player player) {
    super.attack(player);
  }
}
