package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.elements.Element;
import lombok.Getter;

import java.util.Random;

@Getter
public abstract class Actor extends Element {
  protected Random random;
  protected int health;
  protected int attack;
  protected int defence;

  public Actor(Cell cell) {
    super(cell);
    this.cell.setActor(this);
    this.random = new Random();
    this.health = -1;
    this.attack = -1;
    this.defence = -1;
  }

  public void attack(Player player) {
    int playerHealth = player.health;
    int playerDefence = player.getDefence();
    reducePlayerHealth(player, playerHealth, playerDefence);
  }

  private void reducePlayerHealth(Player player, int playerHealth, int playerDefence) {
    player.health = player.health/* + playerDefence*/ - this.attack;
  }

  public boolean playerKilled(Player player) {
    return player.health <= 0;
  }

  public int getX() {
    return cell.getX();
  }

  public int getY() {
    return cell.getY();
  }
}
