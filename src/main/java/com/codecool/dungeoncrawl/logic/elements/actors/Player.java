package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Inventory;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Player extends Actor {

    @Getter
    @Setter
    private String name;

    public Player(Cell cell) {
        super(cell);
        setHealth(100);
        setAttack(20);
        setDefence(10);
    }

    @Getter
    private Inventory inventory = new Inventory();

    public int getAttack() {
        return super.getAttack() + inventory.getAttack();
    }

    public int getDefence() {
        return super.getDefence() + inventory.getDefence();
    }

    public String getTileName() {
        return "player";
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        boolean isMovementOk = isMovementOk(nextCell);
        if (isMovementOk) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    public boolean isMovementOk(Cell nextCell) {
        String monsterInCell = null;
        if (nextCell.getActor() != null) {
            monsterInCell = nextCell.getActor().getTileName();
        }
        String tileInCell = nextCell.getTileName();
        return !(tileInCell.equals("wall") || "skeleton".equals(monsterInCell));
    }

    public boolean isEnemy(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        return nextCell.getActor() != null;
    }

    public void attack(int dx, int dy, ArrayList<Enemy> enemies) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        Enemy attackedEnemy = (Enemy) nextCell.getActor();
        int enemyHealth = attackedEnemy.getHealth();
        attackedEnemy.setHealth(enemyHealth - getAttack());
        if (enemyHealth <= 0) {
            enemies.removeIf(enemy -> enemy.equals(attackedEnemy));
            nextCell.setActor(null);
        }
    }

    public void pickItem() {
        if (cell.getItem() != null) {
            inventory.addItem(cell.getItem());
            cell.removeItem();
        }
    }
}
