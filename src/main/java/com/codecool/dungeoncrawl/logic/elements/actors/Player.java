package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Player extends Actor{
	
	@Getter
	@Setter
	private String name;
	
	public Player(Cell cell){
		super(cell);
		revealNearbyCells();
		setHealth(300);
		setAttack(20);
		setDefence(0);
	}
	
	public Player(Cell cell, int health){
		super(cell);
		revealNearbyCells();
		setHealth(health);
		setAttack(20);
		setDefence(0);
	}
	
	public String getTileName(){
		return "player";
	}

	public void move(int dx, int dy) {
		Cell nextCell = cell.getNeighbor(dx, dy);
		boolean isMovementOk = isMovementOk(nextCell);
		if (isMovementOk) {
			cell.setActor(null);
			nextCell.setActor(this);
			cell = nextCell;
			revealNearbyCells();
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
	
	public boolean isEnemy(int dx, int dy){
		Cell nextCell = cell.getNeighbor(dx, dy);
		return nextCell.getActor() != null;
	}
	
	public boolean isStairs(int dx, int dy){
		Cell nextCell = cell.getNeighbor(dx, dy);
		return nextCell.getType() == CellType.STAIRS;
	}
	
	public void attack(int dx, int dy, ArrayList<Enemy> enemies){
		Cell nextCell = cell.getNeighbor(dx, dy);
		Enemy attackedEnemy = (Enemy) nextCell.getActor();
		int enemyHealthAfterAttack = reduceEnemyHealthAfterAttack(attackedEnemy);
		if(enemyKilled(enemyHealthAfterAttack)){
			removeAttackedEnemy(enemies, nextCell, attackedEnemy);
		}
	}
	
	private int reduceEnemyHealthAfterAttack(Enemy attackedEnemy){
		int enemyHealth = attackedEnemy.getHealth();
		int enemyDefence = attackedEnemy.getDefence();
		attackedEnemy.setHealth(enemyHealth + enemyDefence - this.attack);
		return enemyHealth;
	}
	
	private boolean enemyKilled(int enemyHealth){
		return enemyHealth <= 0;
	}
	
	private void removeAttackedEnemy(ArrayList<Enemy> enemies, Cell nextCell, Enemy attackedEnemy){
		enemies.removeIf(enemy -> enemy.equals(attackedEnemy));
		nextCell.setActor(null);
	}
	
	private void revealNearbyCells(){
		for(int dx = -5;dx < 6;dx++){//check cells in range 3
			for(int dy = -5;dy < 6;dy++){
				try{
					Cell nearbyToPlayer = cell.getNeighbor(dx, dy);
					if(!nearbyToPlayer.isVisible()){
						nearbyToPlayer.setVisible(true);
					}
				}catch(Exception ignored){
				}
			}
		}
	}
}
