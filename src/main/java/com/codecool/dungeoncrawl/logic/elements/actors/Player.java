package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Player extends Actor{
	
	@Getter
	@Setter
	private String name;
	
	public Player(Cell cell){
		super(cell);
		setHealth(100);
		setAttack(20);
		setDefence(10);
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
		}
	}

	public boolean isMovementOk(Cell nextCell) {
		if (cell.getDoor() != null ) {
			if (cell.getDoor().isOpen()) {
				return nextCell.getActor() == null;
			} else {
				//TODO check if key is in inventory
				if (true) {
					//TODO usuwać klucz z inventory
					cell.getDoor().open();
					return true;
				} else {
					return false;
				}
			}
		}
		String tileInCell = nextCell.getTileName();
		return !(tileInCell.equals("wall") || nextCell.getActor() != null);
	}
	
	public boolean isEnemy(int dx, int dy){
		Cell nextCell = cell.getNeighbor(dx, dy);
		return nextCell.getActor() != null;
	}
	
	public void attack(int dx, int dy, ArrayList<Enemy> enemies){
		Cell nextCell = cell.getNeighbor(dx, dy);
		Enemy attackedEnemy = (Enemy) nextCell.getActor();
		int enemyHealth = attackedEnemy.getHealth();
		attackedEnemy.setHealth(enemyHealth - this.attack);
		if(enemyHealth <= 0){
			enemies.removeIf(enemy -> enemy.equals(attackedEnemy));
			nextCell.setActor(null);
		}
	}
}
