package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.items.Inventory;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.ItemType;
import com.codecool.dungeoncrawl.logic.items.Potion;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Player extends Actor {

  @Getter
  private Inventory inventory;
  private final ArrayList<String> developersNames;
  @Getter
  @Setter
  private String name;
  @Getter
  @Setter
  private GameMap map;

  public Player(Cell cell, GameMap map) {
    super(cell);
    revealNearbyCells();
    this.health = 300;
    this.attack = 20;
    this.defence = 0;
    this.map = map;
    this.inventory = new Inventory();
    this.developersNames = new ArrayList<>();
    fillDevsNamesList();
  }

  private void revealNearbyCells() {
    int rangeMinimum = -5;//check cells in range 3
    int rangeMaximum = 6;

    for (int dx = rangeMinimum; dx < rangeMaximum; dx++) {
      for (int dy = rangeMinimum; dy < rangeMaximum; dy++) {
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

  private void fillDevsNamesList() {
    developersNames.add("Marianna");
    developersNames.add("Valeriia");
    developersNames.add("Adam");
    developersNames.add("Mateusz");
    developersNames.add("Michał");
  }

  public Player(Cell cell, int health, String name, Inventory inventory) {
    super(cell);
    revealNearbyCells();
    this.health = health;
    this.attack = 20;
    this.defence = 0;
    this.name = name;
    this.inventory = inventory;
    this.developersNames = new ArrayList<>();
    fillDevsNamesList();
  }

  public int getAttack() {
    return attack + inventory.getAttack();
  }

  public int getDefence() {
    return defence + inventory.getDefence();
  }

  public String getTileName() {
    return "player";
  }

  public void move(int dx, int dy) {
    Cell nextCell = cell.getNeighbor(dx, dy);
    if (nextCell != null) {
      if (developersName()) {
        if (nextCell.getActor() == null) {
          changePositionOnMap(nextCell);
        }
      } else if (isMovementOk(nextCell)) {
        changePositionOnMap(nextCell);
      }
    }
  }

  private boolean developersName() {
    return developersNames.contains(this.name);
  }

  private void changePositionOnMap(Cell nextCell) {
    cell.setActor(null);
    nextCell.setActor(this);
    cell = nextCell;
    revealNearbyCells();
  }

  public boolean isMovementOk(Cell nextCell) {
    if (nextCellWallOrEnemy(nextCell)) return false;
    else if (nextCellNotDoors(nextCell))
      return true;
    else {
      if (nextCellIsOpenedDoors(nextCell))
        return true;
      else return hasKey(nextCell);
    }
  }

  private boolean nextCellWallOrEnemy(Cell nextCell) {
    String tileInCell = nextCell.getTileName();
    return tileInCell.equals("wall") || nextCell.getActor() != null;
  }

  private boolean nextCellNotDoors(Cell nextCell) {
    return nextCell.getDoor() == null;
  }

  private boolean nextCellIsOpenedDoors(Cell nextCell) {
    return nextCell.getDoor().isOpen();
  }

  private boolean hasKey(Cell nextCell) {
    for (Item item : inventory.getItems()) {
      if (openDoorsPossible(item)) {
        openDoors(nextCell, item);
        return true;
      }
    }
    return false;
  }

  private boolean openDoorsPossible(Item item) {
    return item.getType() == ItemType.KEY;
  }

  private void openDoors(Cell nextCell, Item item) {
    inventory.removeItem(item);
    nextCell.getDoor().open();
  }

  public boolean isEnemy(int dx, int dy) {
    Cell nextCell = cell.getNeighbor(dx, dy);
    return nextCell.getActor() != null;
  }

  public boolean isStairs(int dx, int dy) {
    Cell nextCell = cell.getNeighbor(dx, dy);
    return nextCell.getType() == CellType.STAIRS;
  }

  public void pickItem() {
    Item pickedItem = cell.getItem();

    if (pickedItem.getType() == ItemType.POTION) {
      Potion potion = (Potion) pickedItem;
      this.health += potion.getHealthAddingValue();
    } else {
      inventory.addItem(cell.getItem());
    }
    cell.removeItem();
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
//    int enemyDefence = attackedEnemy.defence;
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
}
