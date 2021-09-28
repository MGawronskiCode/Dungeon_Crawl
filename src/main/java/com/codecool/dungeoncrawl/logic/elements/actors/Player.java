package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.items.Inventory;
import com.codecool.dungeoncrawl.logic.items.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Player extends Actor {//todo tests

  @Getter
  private final Inventory inventory = new Inventory();
  @Getter
  @Setter
  private String name;
  @Getter
  private GameMap map;

  public Player(Cell cell, GameMap map) {
    super(cell);
    revealNearbyCells();
    this.health = 300;
    this.attack = 20;
    this.defence = 0;
    this.map = map;
  }

  private void revealNearbyCells() {
    for (int dx = -5; dx < 6; dx++) {//check cells in range 3
      for (int dy = -5; dy < 6; dy++) {
        try {
          Cell nearbyToPlayer = cell.getNeighbor(dx, dy);
          if (!nearbyToPlayer.isVisible()) {
            nearbyToPlayer.setVisible(true);
          }
        } catch (Exception ignored) {
        }
      }
    }
  }

  public Player(Cell cell, int health) {
    super(cell);
    revealNearbyCells();
    this.health = health;
    this.attack = 20;
    this.defence = 0;
  }

  public int getAttack() {
    return attack + inventory.getAttack();
  }

  public int getDefence() {
    return attack + inventory.getDefence();
  }

  public String getTileName() {
    return "player";
  }

  public void move(int dx, int dy) {
    Cell nextCell = cell.getNeighbor(dx, dy);
    if (nextCell != null) {
      boolean isMovementOk = isMovementOk(nextCell);
      if (isMovementOk) {
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
        revealNearbyCells();
      }
    }
  }

  public boolean isMovementOk(Cell nextCell) {//powydzielać do funkcji
    if (nextCell.getDoor() != null) {
      if (nextCell.getDoor().isOpen()) {
        return nextCell.getActor() == null;
      } else {
        for (Item item : inventory.getItems()) {
          if (item.getTileName().equals("key")) {//todo inventory method hasKey
            inventory.removeItem(item);
            nextCell.getDoor().open();
            return true;
          }
        }
        return false;
      }
    }
    String tileInCell = nextCell.getTileName();
    return !(tileInCell.equals("wall") || nextCell.getActor() != null);
  }

  public boolean isEnemy(int dx, int dy) {
    Cell nextCell = cell.getNeighbor(dx, dy);
    return nextCell.getActor() != null;
  }

  public boolean isStairs(int dx, int dy) {
    Cell nextCell = cell.getNeighbor(dx, dy);
    return nextCell.getType() == CellType.STAIRS;
  }

  public boolean hasKey() {
    return inventory.getItems().contains("key");
  }

  public void attack(int dx, int dy, ArrayList<Enemy> enemies) {
    Cell nextCell = cell.getNeighbor(dx, dy);
    Enemy attackedEnemy = (Enemy) nextCell.getActor();
    int enemyHealthAfterAttack = reduceEnemyHealthAfterAttack(attackedEnemy);
    if (enemyKilled(enemyHealthAfterAttack)) {
      removeAttackedEnemy(enemies, nextCell, attackedEnemy);
    }
  }

  private int reduceEnemyHealthAfterAttack(Enemy attackedEnemy) {
    int enemyHealth = attackedEnemy.health;
    int enemyDefence = attackedEnemy.defence;
    attackedEnemy.health = (enemyHealth/* + enemyDefence*/ - this.attack);
    return enemyHealth;
  }

  private boolean enemyKilled(int enemyHealth) {
    return enemyHealth <= 0;
  }

  private void removeAttackedEnemy(ArrayList<Enemy> enemies, Cell nextCell, Enemy attackedEnemy) {
    enemies.removeIf(enemy -> enemy.equals(attackedEnemy));
    nextCell.setActor(null);
  }

  public void pickItem() {
    if (cell.getItem() != null) {
      inventory.addItem(cell.getItem());
      cell.removeItem();
    }
  }
}
