package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Troll extends Enemy {
  private boolean canMove = true;

  public Troll(Cell cell) {
    super(cell);
    this.health = 200;
    this.attack = 25;
    this.defence = 0;
  }

  @Override
  public String getTileName() {
    return "troll";
  }

  @Override
  public void makeMove(Player player) {
    if (canMove) {
      super.makeMove(player);
      canMove = false;
    } else
      canMove = true;
  }
}
