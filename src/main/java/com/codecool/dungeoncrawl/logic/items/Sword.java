package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

import java.util.Random;

public class Sword extends Item {

  public int attack;

  public Sword(Cell cell) {
    super(cell, "sword");
    this.attack = new Random().nextInt(5) + 5;
  }

  @Override
  public String getTileName() {
    return "sword";
  }

  @Override
  public String toString() {
    return "Sword + " + attack;
  }

  public int getAttack() {
    return attack;
  }
}
