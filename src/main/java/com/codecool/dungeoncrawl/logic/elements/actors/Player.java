package com.codecool.dungeoncrawl.logic.elements.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import lombok.Getter;
import lombok.Setter;

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
	
	public void move(int dx, int dy){
		Cell nextCell = cell.getNeighbor(dx, dy);
		cell.setActor(null);
		nextCell.setActor(this);
		cell = nextCell;
	}
}
